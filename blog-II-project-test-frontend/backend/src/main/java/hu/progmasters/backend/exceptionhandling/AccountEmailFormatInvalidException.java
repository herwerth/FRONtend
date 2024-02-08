package hu.progmasters.backend.exceptionhandling;

public class AccountEmailFormatInvalidException extends RuntimeException {

    private String email;

    public AccountEmailFormatInvalidException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
