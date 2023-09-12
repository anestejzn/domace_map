package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.UserResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.enums.EntityType;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.RegistrationVerification;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Role;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.User;
import rs.ac.uns.ftn.siit.sw442019.graduate.repository.UserRepository;
import rs.ac.uns.ftn.siit.sw442019.graduate.security.JWTUtils;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IUserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Helper.passwordsDontMatch;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegularUserService regularUserService;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private RoleService roleService;

    @Override
    public User getVerifiedUser(String email) throws EntityNotFoundException {
        return userRepository.getVerifiedUser(email)
                .orElseThrow(() -> new EntityNotFoundException(email, EntityType.USER));
    }


    @Override
    public boolean checkIfUserAlreadyExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public UserResponse create(
            String email,
            String name,
            String surname,
            String password,
            String confirmPassword
    ) throws EntityAlreadyExistsException, PasswordsDoNotMatchException, IOException, MailCannotBeSentException {
        if (passwordsDontMatch(password, confirmPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if (this.checkIfUserAlreadyExists(email)) {
            throw new EntityAlreadyExistsException(String.format("User with email %s already exists.", email));
        }

        RegistrationVerification verification = verificationService.create(email);
        Role role = roleService.getRoleByName("ROLE_USER");

        return regularUserService.create(
                email, name, surname, password, role, verification.getId()
        );
    }

    @Override
    public boolean activate(Long verifyId, int securityCode) throws EntityNotFoundException, WrongVerifyTryException {
        RegistrationVerification verify = verificationService.update(verifyId, securityCode);
        regularUserService.activateAccount(verify.getUserEmail());
        return true;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

}
