package Model;

public abstract class User {

    private String username;
    private Organization organization;

    public String getUsername() {
        return username;
    }

    public Organization getOrganization() {
        return organization;
    }
}
