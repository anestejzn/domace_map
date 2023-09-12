package rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces;


import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.HouseholdResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.UserResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.EntityNotFoundException;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.RegularUser;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Role;

public interface IRegularUserService {

    RegularUser getRegularUserByEmail(String email) throws EntityNotFoundException;
    UserResponse create(
            String email,
            String name,
            String surname,
            String password,
            Role role,
            Long verificationId
            );
    boolean activateAccount(String userEmail) throws EntityNotFoundException;
    RegularUser getRegularUserById(Long id) throws EntityNotFoundException;
    boolean block(RegularUser regularUser);

    boolean unblock(Long userId) throws EntityNotFoundException;
    HouseholdResponse getHouseholdForUser(Long userId) throws EntityNotFoundException;
    RegularUser save(RegularUser user);
}
