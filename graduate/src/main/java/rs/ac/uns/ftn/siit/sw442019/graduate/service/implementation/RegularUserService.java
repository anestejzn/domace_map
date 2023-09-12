package rs.ac.uns.ftn.siit.sw442019.graduate.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.HouseholdResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.UserResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.enums.EntityType;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.RegularUser;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Role;
import rs.ac.uns.ftn.siit.sw442019.graduate.repository.RegularUserRepository;
import rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces.IRegularUserService;

import java.util.List;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.SALT_LENGTH;
import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.ZERO_FAILED_ATTEMPTS;
import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Helper.generateRandomString;
import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Helper.getHash;

@Component
public class RegularUserService implements IRegularUserService {

    @Autowired
    private RegularUserRepository regularUserRepository;

    public RegularUser getRegularUserByEmail(String email) throws EntityNotFoundException {
        return regularUserRepository.getRegularUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(email, EntityType.USER));
    }

    public RegularUser getRegularUserById(Long id) throws EntityNotFoundException {
        return regularUserRepository.getRegularUserById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, EntityType.USER));
    }

    public UserResponse create(
            String email,
            String name,
            String surname,
            String password,
            Role role,
            Long verificationId
    ) {
        String salt = generateRandomString(SALT_LENGTH);
        String hashedPassword = getHash(password);
        RegularUser regularUser = regularUserRepository.save(
                new RegularUser(email, hashedPassword, name, surname, salt,
                        ZERO_FAILED_ATTEMPTS, null, role
        ));

        return new UserResponse(regularUser, verificationId);
    }

    public boolean activateAccount(String userEmail) throws EntityNotFoundException {
        RegularUser regularUser = this.getRegularUserByEmail(userEmail);
        regularUser.setVerified(true);
        regularUserRepository.save(regularUser);
        return true;
    }

    public boolean block(RegularUser regularUser) {
        regularUserRepository.save(regularUser);

        return true;
    }

    public boolean unblock(Long userId) throws EntityNotFoundException {
        RegularUser regularUser = this.getRegularUserById(userId);
        regularUserRepository.save(regularUser);

        return true;
    }

    public HouseholdResponse getHouseholdForUser(Long userId) throws EntityNotFoundException {
        RegularUser regularUser = this.getRegularUserById(userId);
        return regularUser.getHousehold() != null ? new HouseholdResponse(regularUser.getHousehold()) : null;
    }

    @Override
    public RegularUser save(RegularUser user) {
        return regularUserRepository.save(user);
    }

    public List<RegularUser> getAll() {
        return regularUserRepository.findAll();
    }
}
