package hu.progmasters.backend.exceptionhandling;

public class AccountNotFoundByIdException extends RuntimeException {

    private Long id;

    public AccountNotFoundByIdException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
