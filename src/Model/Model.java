package Model;

import DB.DBManager;
import View.View;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import Controller.Controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class Model implements Observable {

    private DBManager dbManager;
    static int eventID = 0;

    public Model(Controller controller)
    {
        this.dbManager = new DBManager();
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
        if (dbManager.addEvent(event) && dbManager.addCategoriesOfEvent(event) && dbManager.addResponsiblesOfEvent(event)){
            eventID ++;
            return true;
        }
        return false;
    }

    public String getFirstUpdate(int eventId) throws ParseException {
        Event e = getEvent(eventId);
        return e.getFirstUpdate().getContent();
    }

    public ArrayList<Integer> getEventsByCategory (String categoryName) {
        return dbManager.getEventsByCategory(categoryName);
    }

    public Event getEvent (int eventID) throws ParseException {
        return dbManager.getEvent(eventID);
    }

    public ArrayList<Permission> getPermissionsOfEvent (String username, int eventID) {
        return dbManager.getPermissionsOfEvent(username,eventID);
    }

    public boolean createUpdate (Update update) {
        String[] prevUpdate = dbManager.getLastUpdate(update.getEventId());
        int lastOrder = Integer.parseInt(prevUpdate[0]);
        String lastUpdate = prevUpdate[1];
        update.setOrderId(lastOrder + 1);
        update.setLastUpdate(lastUpdate);
        return dbManager.addUpdate(update);
    }

    public boolean login(String username, String password) {
        return dbManager.login(username, password);
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }


    public static void main(String[] args) throws ParseException {
        View view = new View();
        Controller controller=new Controller(view);
        Model m = new Model(controller);
      //  ArrayList<String> categories = m.getAllCategories();
      //  ArrayList<String> firemen = m.getUsersByOrganization("FD");
/*        ArrayList<Integer> events = m.getEventsByCategory("fire");
        for (Integer e: events) {
            System.out.println(e);
        }
        Event e = m.getEvent(1);
        System.out.println(e.getTitle());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        System.out.println(formatter.format(e.getDate()));

        System.out.println(m.getFirstUpdate(2));*/

        //create update
/*        Update up = new Update("Evacuation of the injured",2,"sha","20-05-2019");
        m.createUpdate(up);

        ArrayList<Permission> permissions = m.getPermissionsOfEvent("sha",1);
        for (Permission p: permissions) {
            System.out.println(p.getPermission());
        }*/

        //create event
        ArrayList<Category> cat = new ArrayList<>();
        cat.add(new Category("fire"));
        cat.add(new Category("accident"));
        ArrayList<SecurityForceUser> resp = new ArrayList<>();
        resp.add(new SecurityForceUser("eini",new Organization("PD")));
        resp.add(new SecurityForceUser("nit",new Organization("FD")));
        Update fUp = new Update("5 injured people on the road");
        Event e2 = new Event("accident in road 90",cat,"08-06-2019","nitza",fUp,resp);
        System.out.println(m.createEvent(e2));

/*        ArrayList<Category> cat2 = new ArrayList<>();
        cat.add(new Category("accident"));
        ArrayList<SecurityForceUser> resp2 = new ArrayList<>();
        resp.add(new SecurityForceUser("eini",new Organization("PD")));
        Update fUp2 = new Update("2 injured people on the road");
        Event e22 = new Event("accident in road 70",cat2,"10-06-2019","nitza",fUp2,resp2);
        System.out.println(m.createEvent(e22));*/
    }
}
