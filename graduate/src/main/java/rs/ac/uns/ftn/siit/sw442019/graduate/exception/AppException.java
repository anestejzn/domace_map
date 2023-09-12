package rs.ac.uns.ftn.siit.sw442019.graduate.exception;

import rs.ac.uns.ftn.siit.sw442019.graduate.enums.EntityType;

import static rs.ac.uns.ftn.siit.sw442019.graduate.enums.EntityType.getEntityErrorMessage;

public class AppException extends Exception{

    private final String message;

    public AppException(String id, EntityType entityType) {
        super();
        this.message = createExceptionMessage(id, entityType);
    }

    public AppException(String message) {
        super();
        this.message = message;
    }

    private String createExceptionMessage(String id, EntityType entityType) {
        return getEntityErrorMessage(id, entityType);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
