package hu.progmasters.backend.exceptionhandling;

public class NewPasswordEqualsOldPasswordException extends RuntimeException {

    private String newPassword;

    public NewPasswordEqualsOldPasswordException(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
