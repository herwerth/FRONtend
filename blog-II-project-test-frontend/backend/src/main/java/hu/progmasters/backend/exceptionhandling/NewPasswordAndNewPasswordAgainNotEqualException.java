package hu.progmasters.backend.exceptionhandling;

public class NewPasswordAndNewPasswordAgainNotEqualException extends RuntimeException {

    private String newPassword;

    private String newPasswordAgain;

    public NewPasswordAndNewPasswordAgainNotEqualException(String newPassword, String newPasswordAgain) {
        this.newPassword = newPassword;
        this.newPasswordAgain = newPasswordAgain;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getNewPasswordAgain() {
        return newPasswordAgain;
    }
}
