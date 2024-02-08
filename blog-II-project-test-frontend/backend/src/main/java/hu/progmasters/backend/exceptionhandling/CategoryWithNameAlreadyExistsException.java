package hu.progmasters.backend.exceptionhandling;

public class CategoryWithNameAlreadyExistsException extends RuntimeException {

    private String name;

    public CategoryWithNameAlreadyExistsException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
