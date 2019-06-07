package Model;

import DB.DBManager;
import View.View;
import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import Controller.Controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class Model implements Observable {

    private DBManager dbManager;
    static int eventID = 0;

    public Model(Controller controller)
    {
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

    public boolean createEvent(Event event) {
        event.setId(eventID);
        event.setStatus(EventStatus.IN_TREATMENT);
        if (dbManager.addEvent(event) && dbManager.addCategoriesOfEvent(event) && dbManager.addResponsiblesOfEvent(event)){
            eventID ++;
            return true;
        }
        return false;
    }

    public Update getFirstUpdate(int eventId) throws ParseException {
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

    public boolean login(String username, String password) {

        return false;
    }




    public static void main(String[] args) throws ParseException {
        View view = new View();
        Controller controller=new Controller(view);
        Model m = new Model(controller);
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
