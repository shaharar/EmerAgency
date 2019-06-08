package Model;

public abstract class User {

    private String username;
    private Organization organization;
    private int rank;

    private Permission[] permissions;

    public User(String username, Organization organization, int rank) {
        this.username = username;
        this.organization = organization;
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Permission[] getPermissions() {
        return permissions;
    }

    public void setPermissions(Permission[] permissions) {
        this.permissions = permissions;
    }
}
