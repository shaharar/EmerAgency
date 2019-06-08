package Model;

public class Permission {

    private int eventId;
    private String rUsername;
    private String permission;

    public Permission(String username, int eventId, String permission) {
        this.eventId = eventId;
        this.rUsername = username;
        this.permission = permission;
    }

    public int getEventId() {
        return eventId;
    }

    public String getrUsername() {
        return rUsername;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
