package pl.ml.user;

public enum Role {
    ROLE_USER("Użytkownik"),
    ROLE_ADMIN("Administrator");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
