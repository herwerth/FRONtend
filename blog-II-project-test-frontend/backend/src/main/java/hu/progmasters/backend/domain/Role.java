package hu.progmasters.backend.domain;

public enum Role {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String roles;

    Role(String roles) {
        this.roles = roles;
    }

    public String getRoles() {
        return roles;
    }
}
