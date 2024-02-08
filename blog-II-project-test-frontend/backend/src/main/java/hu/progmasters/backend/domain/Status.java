package hu.progmasters.backend.domain;

public enum Status {

    ACTIVE("active"),

    INACTIVE("inactive"),

    DELETED("deleted");

    private String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
