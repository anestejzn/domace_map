package rs.ac.uns.ftn.siit.sw442019.graduate.exception;


import rs.ac.uns.ftn.siit.sw442019.graduate.enums.EntityType;

public class EntityNotFoundException extends AppException {

    public EntityNotFoundException(String id, EntityType entityType) {
        super(id, entityType);
    }

    public EntityNotFoundException(Long id, EntityType entityType) {
        super(id.toString(), entityType);
    }
}
