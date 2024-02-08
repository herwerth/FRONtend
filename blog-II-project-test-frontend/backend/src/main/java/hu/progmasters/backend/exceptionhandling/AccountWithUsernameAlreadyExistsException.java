package hu.progmasters.backend.exceptionhandling;

public class AccountWithUsernameAlreadyExistsException extends RuntimeException {

    private String username;

    public AccountWithUsernameAlreadyExistsException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
