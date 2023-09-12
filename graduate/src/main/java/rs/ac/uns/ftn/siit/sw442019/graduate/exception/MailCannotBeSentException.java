package rs.ac.uns.ftn.siit.sw442019.graduate.exception;

public class MailCannotBeSentException extends AppException {

    public MailCannotBeSentException(String email) {
        super(String.format("Something went wrong. Email to %s" +
            " cannot be sent.", email));
    }
}
