package Model;

import DB.DBManager;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
    public String getFirstUpdate(int eventId) throws ParseException {
        Event e = getEvent(eventId);
        return e.getFirstUpdate();
    }

    public ArrayList<String> getEventsByCategory (String categoryName) {
        return dbManager.getEventsByCategory(categoryName);
    }

    public Event getEvent (int eventID) throws ParseException {
        return dbManager.getEvent(eventID);
    }

//    public String getPermissionOfEvent (String username, String eventID) {
//
//    }
//
//    public String createUpdate (String username, String eventInfo, Date updateDate) {
//
//    }



    public static void main(String[] args) throws ParseException {
        Model m = new Model();
        ArrayList<String> categories = m.getAllCategories();
        ArrayList<String> firemen = m.getUsersByOrganization("FD");
        ArrayList<String> events = m.getEventsByCategory("fire");
        for (String e: events) {
            System.out.println(e);
        }
        Event e = m.getEvent(1);
        System.out.println(e.getTitle());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        System.out.println(formatter.format(e.getDate()));

        System.out.println(m.getFirstUpdate(1));

    }
}
