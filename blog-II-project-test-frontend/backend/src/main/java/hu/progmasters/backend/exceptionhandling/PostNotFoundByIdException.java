package hu.progmasters.backend.exceptionhandling;

public class PostNotFoundByIdException extends RuntimeException {

    private Long id;

    public PostNotFoundByIdException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
