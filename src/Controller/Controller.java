package Controller;

import Model.Model;
import Model.User;
import Model.Event;
import Model.Update;
import Model.Permission;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Controller extends Observable implements Observer {

    private Model model;

//    public HashSet<String> categories;


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

//    public void openwindow(String fxmlfile, Object Parameter) {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        Parent root = null;
//        try {
//            root = fxmlLoader.load(getClass().getClassLoader().getResource(fxmlfile).openStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Stage newStage = new Stage();
//        Scene scene = new Scene(root, 600, 457);
//        newStage.setScene(scene);
//
//        View NewWindow = fxmlLoader.getController();
//        // NewWindow.setStage(newStage);
//        NewWindow.setController(this);
//        newStage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
//        newStage.show();
//    }

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
        if(permissions.contains("read"))
            return model.getEvent(eventId);
        else
            return null;
    }

/*    public void edit(String eventId, String updateId, String newContent){

    }*/


    public void setModel(Model model) {
        this.model = model;
    }

//    public String getOrganizationOfUser(String username) {
//        return model.getOrganizationOfUser(username);
//    }
//
//    public int getRankOfUser(String username) {
//        return model.getRankOfUser(username);
//    }

    public User getUser(String username) {
        return model.getUser(username);
    }

    public ArrayList<Integer> getEvetnsByCategory(String c) {
        return model.getEventsByCategory(c);
    }

    public ArrayList<String> getUsersByOrg(String organization) {
        return model.getUsersByOrganization(organization);
    }


}
