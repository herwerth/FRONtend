package hu.progmasters.backend.exceptionhandling;

public class OldPasswordNotCorrectException extends RuntimeException {

    private String oldPassword;

    public OldPasswordNotCorrectException(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }
}
