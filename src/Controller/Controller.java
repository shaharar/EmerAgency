package Controller;

import Model.Model;
import Model.User;
import Model.Event;
import Model.Update;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Controller extends Observable implements Observer {

    private Model model;

    public Controller() {
        this.model = new Model();
    }

    public ArrayList<String> getCategories() {
        return model.getAllCategories();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
//        try{
//            Object obj = ((Object[])arg)[0];
//            String str = (String)obj;
//            switch(str){
//                case "create vacation failed":
//                    showAlert("Create Vacation Failed", "Create vacation failed, please try again.");
//                    break;
//
//                case "create vacation succeeded":
//                    //openRud(txt_id_user.getText());
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                    alert.setTitle("Create Vacation Succeeded");
//                    Optional<ButtonType> reasult = alert.showAndWait();
//                    if(reasult.get() == ButtonType.OK)
//                        alert.close();
//                    Stage prim = (Stage) txt_title.getScene().getWindow();
//                    prim.close();
//                    break;
//
//            }
//        } catch (Exception e){
//
//        }
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
