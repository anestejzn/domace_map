package rs.ac.uns.ftn.siit.sw442019.graduate.exception;


import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.PASSWORDS_DO_NOT_MATCH_MESSAGE;

public class PasswordsDoNotMatchException extends AppException {

    public PasswordsDoNotMatchException() {
        super(PASSWORDS_DO_NOT_MATCH_MESSAGE);
    }

    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
