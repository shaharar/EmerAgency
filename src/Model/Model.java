package Model;

import DB.DBManager;
import java.text.ParseException;
import java.util.ArrayList;


public class Model {

    private static Model myModel = null;
    private DBManager dbManager;
    static int eventID;
    private String currUsername;

    private Model() {
        this.dbManager = DBManager.getInstance();
        this.currUsername = "";
        eventID = dbManager.getLastEventId() + 1;
    }

    public static Model getInstance() {
        if (myModel == null){
            myModel = new Model();
        }
        return myModel;
    }

    public ArrayList<String> getAllCategories() {
        ArrayList<String> categories = dbManager.getCategories();
        return categories;
    }


    public ArrayList<String> getUsersByOrganization(String organization) {
        ArrayList<String> users = dbManager.getUsersByOrganization(organization);
        return users;
    }

    public User getUser (String username){
        return dbManager.getUser(username);
    }

    public boolean createEvent(Event event) {
        User currUser = dbManager.getUser(currUsername);
        if (currUser != null) { //valid user is logged in
            if (!currUser.getOrganization().getName().equals("SD")) { //only service center user may create event
                return false;
            }
            event.setId(eventID);
            event.setPostedBy(currUsername);
            if (dbManager.addEvent(event) && dbManager.addCategoriesOfEvent(event) && dbManager.addResponsiblesOfEvent(event)){
                eventID ++;
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> getEventsByCategory (String categoryName) {
        return dbManager.getEventsByCategory(categoryName);
    }

    public Event getEvent (int eventID) throws ParseException {
        return dbManager.getEvent(eventID);
    }

    public ArrayList<String> getPermissionsOfEvent (String username, int eventID) {
        return dbManager.getPermissionsOfEvent(username,eventID);
    }

    public boolean createUpdate(Update update) {
        String[] prevUpdate = dbManager.getLastUpdate(update.getEventId());
        int lastOrder = Integer.parseInt(prevUpdate[0]);
        String lastUpdate = prevUpdate[1];
        update.setOrderId(lastOrder + 1);
        update.setLastUpdate(lastUpdate);
        return dbManager.addUpdate(update);
    }

    public boolean login(String username, String password) {
        if (dbManager.login(username, password)){
            currUsername = username;
            return true;
        }
        return false;
    }

    public void logout() {
        currUsername = "";
    }

    public String getOrganizationOfUser(String username) {
        return dbManager.getOrganizationOfUser(username);
    }

    public ArrayList<String> getAllUsers() {
        return dbManager.getAllUsers();
    }

    public int getRankOfUser(String username) {
        return dbManager.getRankOfUser(username);
    }

    public String getFirstUpdate(int eventId) throws ParseException {
        Event e = getEvent(eventId);
        return e.getFirstUpdate();
    }
}
