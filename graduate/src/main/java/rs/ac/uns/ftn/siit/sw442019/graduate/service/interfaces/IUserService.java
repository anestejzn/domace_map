package rs.ac.uns.ftn.siit.sw442019.graduate.service.interfaces;



import rs.ac.uns.ftn.siit.sw442019.graduate.dto.response.UserResponse;
import rs.ac.uns.ftn.siit.sw442019.graduate.exception.*;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.User;
import java.io.IOException;

public interface IUserService {
    User getVerifiedUser(String email) throws EntityNotFoundException;
    UserResponse create(
            String email,
            String name,
            String surname,
            String password,
            String confirmPassword
    ) throws EntityAlreadyExistsException, PasswordsDoNotMatchException, IOException, MailCannotBeSentException;
    boolean checkIfUserAlreadyExists(String email);
    boolean activate(Long verifyId, int securityCode) throws EntityNotFoundException, WrongVerifyTryException;
    User save(User user);
}
