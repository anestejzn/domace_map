package rs.ac.uns.ftn.siit.sw442019.graduate.exception;


import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.UPDATE_ERROR_MESSAGE;

public class EntityUpdateException extends AppException {

    public EntityUpdateException() {
        super(UPDATE_ERROR_MESSAGE);
    }

    public EntityUpdateException(String message) {super(message);}
}