package Model;

public abstract class User {

    private String username;
    private Organization organization;
    private Permission[] permissions;

    public User(String username, Organization organization) {
        this.username = username;
        this.organization = organization;
    }

    public String getUsername() {
        return username;
    }

    public Organization getOrganization() {
        return organization;
    }
}
