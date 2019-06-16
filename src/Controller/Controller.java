package Controller;

import Model.Model;
import Model.User;
import Model.Event;
import Model.Update;
import java.text.ParseException;
import java.util.ArrayList;

public class Controller {

    private static Controller myController = null;
    private Model model;

    private Controller() {
        this.model = Model.getInstance();
    }

    public static Controller getInstance() {
        if (myController == null){
            myController = new Controller();
        }
        return myController;
    }

    public ArrayList<String> getCategories() {
        return model.getAllCategories();
    }

    public boolean login(String username, String password) {
        return  (model.login(username, password));
    }

    public boolean createEvent(Event event){
        return model.createEvent(event);
    }

    public boolean createUpdate(Update update){
        return model.createUpdate(update);
    }

    public Event watch(String uname, int eventId) throws ParseException {
        ArrayList<String> permissions = model.getPermissionsOfEvent(uname, eventId);
        if(!model.getOrganizationOfUser(uname).equals("SD")){
            if(!permissions.contains("read")) {
                return null;
            }
        }
        return model.getEvent(eventId);
    }


    public void setModel(Model model) {
        this.model = model;
    }

    public User getUser(String username) {
        return model.getUser(username);
    }

    public ArrayList<Integer> getEvetnsByCategory(String c) {
        return model.getEventsByCategory(c);
    }

    public ArrayList<String> getUsersByOrg(String organization) {
        return model.getUsersByOrganization(organization);
    }

    public void logout() {
        model.logout();
    }
}
