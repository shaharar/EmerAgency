package Model;

import DB.DBManager;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.Date;

public class Model implements Observable {

    private DBManager dbManager;

    public Model() {
        this.dbManager = new DBManager();
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }

    public ArrayList<String> getAllCategories() {
        ArrayList<String> categories = dbManager.getCategories();
        return categories;
    }


    public ArrayList<String> getUsersByOrganization(String organization) {
        ArrayList<String> users = dbManager.getUsersByOrganization(organization);
        return users;
    }

//    public void createEvent(String username, String title, Date publishDate, int updateID) {
//
//    }
//
//    public String getFirstUpdate() {
//
//    }
//
    public ArrayList<String> getEventsByCategory (String categoryName) {
        return dbManager.getEventsByCategory(categoryName);
    }
//
//    public String[] getEvent (String eventID) {
//
//    }
//
//    public String getPermissionOfEvent (String username, String eventID) {
//
//    }
//
//    public String createUpdate (String username, String eventInfo, Date updateDate) {
//
//    }



    public static void main(String[] args) {
        Model m = new Model();
        ArrayList<String> categories = m.getAllCategories();
        ArrayList<String> firemen = m.getUsersByOrganization("FD");
        ArrayList<String> events = m.getEventsByCategory("fire");
        for (String e: events) {
            System.out.println(e);
        }
    }
}
