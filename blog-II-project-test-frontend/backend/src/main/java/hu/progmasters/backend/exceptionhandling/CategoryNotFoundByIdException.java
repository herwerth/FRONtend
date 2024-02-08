package hu.progmasters.backend.exceptionhandling;

public class CategoryNotFoundByIdException extends RuntimeException {

    private Long id;

    public CategoryNotFoundByIdException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
