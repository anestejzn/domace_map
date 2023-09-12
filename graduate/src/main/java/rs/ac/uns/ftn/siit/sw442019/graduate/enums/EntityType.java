package rs.ac.uns.ftn.siit.sw442019.graduate.enums;

public enum EntityType {
    USER,
    OFFER,
    VERIFY;
    public static String getEntityErrorMessage(String id, EntityType entityType) {
        if (entityType == EntityType.USER) {
            return "User is not found.";
        } else if (entityType == EntityType.OFFER) {
            return "Offer is not found.";
        } else {
            return "Verify is not found";
        }
    }
}
