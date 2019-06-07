package Model;

public abstract class User {

    private String username;
    private Organization organization;
    private Permission[] permissions;

    public String getUsername() {
        return username;
    }

    public Organization getOrganization() {
        return organization;
    }
}
