package hu.progmasters.backend.exceptionhandling;

public class PasswordFormatInvalidException extends RuntimeException {

    private String password;

    public PasswordFormatInvalidException(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
