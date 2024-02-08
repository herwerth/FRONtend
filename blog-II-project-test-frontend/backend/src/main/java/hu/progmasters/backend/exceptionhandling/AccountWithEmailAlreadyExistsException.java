package hu.progmasters.backend.exceptionhandling;

public class AccountWithEmailAlreadyExistsException extends RuntimeException {

    private String email;

    public AccountWithEmailAlreadyExistsException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
