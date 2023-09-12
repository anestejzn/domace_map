package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.VerifyMailResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.enums.EntityType;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.MailCannotBeSentException;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.WrongVerifyTryException;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.RegistrationVerification;
import rs.ac.uns.ftn.siit.sw442019.graduate.repository.VerificationRepository;

import java.io.IOException;
import java.time.LocalDateTime;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.*;
import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Helper.*;


@Service
public class VerificationService {

    @Autowired
    private VerificationRepository verificationRepository;

    @Autowired
    private EmailService emailService;

    public RegistrationVerification get(Long id) throws EntityNotFoundException {

        if(verificationRepository.getRegistrationVerificationsById(id).isPresent()){
            return verificationRepository.getRegistrationVerificationsById(id).get();
        }
        else{
            throw new EntityNotFoundException(id, EntityType.VERIFY);
        }
    }

    public RegistrationVerification getByHashedId(String id) throws EntityNotFoundException {

        if(verificationRepository.getRegistrationVerificationsByHashedId(id).isPresent()){
            return verificationRepository.getRegistrationVerificationsByHashedId(id).get();
        }
        else{
            throw new EntityNotFoundException(id, EntityType.VERIFY);
        }
    }

    public RegistrationVerification update(Long verifyId, int securityCode) throws EntityNotFoundException, WrongVerifyTryException {
        RegistrationVerification verify = verificationRepository.findById(verifyId)
                .orElseThrow(() -> new EntityNotFoundException(verifyId, EntityType.VERIFY));
        if (verify.canVerify(String.valueOf(securityCode))) {
            verify.incrementNumOfTries();
            this.saveChanges(verify, true);

            return verify;
        } else if (verify.wrongCodeButHasTries()){
            this.saveChanges(verify, verify.incrementNumOfTries() >= MAX_NUM_VERIFY_TRIES);
            throw new WrongVerifyTryException("Your security code is not accepted. Try again.");
        } else {
            saveChanges(verify, true);
            throw new WrongVerifyTryException("Your verification code is either expired or typed wrong 3 times. Reset code.");
        }
    }

    private void saveChanges(final RegistrationVerification verify, final boolean used) {
        verify.setUsed(used);
        verificationRepository.save(verify);
    }

    public RegistrationVerification create(String email) throws IOException, MailCannotBeSentException {
        String salt = generateRandomString(SALT_LENGTH);
        int securityCode = generateSecurityCode();
        RegistrationVerification registrationVerification = new RegistrationVerification(
                getHash(String.valueOf(securityCode)),
                ZERO_FAILED_ATTEMPTS,
                email,
                LocalDateTime.now().plusMinutes(10),
                salt,
                generateHashForURL(salt + email)
        );

        RegistrationVerification verify = verificationRepository.save(registrationVerification);
        this.sendVerificationEmail(new VerifyMailResponse(verify.getId(), securityCode, registrationVerification.getHashedId()), email);

        return verify;
    }

    public void generateNewSecurityCode(String verifyHash)
            throws EntityNotFoundException, IOException, MailCannotBeSentException
    {
        RegistrationVerification verify = getByHashedId(verifyHash);
        create(verify.getUserEmail());
        verificationRepository.delete(verify);
    }

    public void sendVerificationEmail(VerifyMailResponse verifyMailResponse, String to)
            throws IOException, MailCannotBeSentException
    {
        emailService.sendVerificationMail(
                verifyMailResponse.getSecurityCode(),
                String.format("%s%s", FRONT_VERIFY_URL, verifyMailResponse.getId()), to
        );
    }
}
