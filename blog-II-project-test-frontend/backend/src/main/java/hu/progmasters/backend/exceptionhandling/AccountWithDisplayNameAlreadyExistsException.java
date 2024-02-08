package hu.progmasters.backend.exceptionhandling;

public class AccountWithDisplayNameAlreadyExistsException extends RuntimeException {

    private String displayName;

    public AccountWithDisplayNameAlreadyExistsException(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
