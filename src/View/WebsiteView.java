package View;

import Controller.Controller;
import Model.Event;
import Model.Organization;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class WebsiteView extends MainView implements Observer {

    public javafx.scene.control.Button btn_Create;
    public javafx.scene.control.Button btn_Watch;
    public javafx.scene.control.Button btn_CreateUpdate;
    public javafx.scene.control.Button btn_LogOut;
    public javafx.scene.control.ComboBox categoryMenu;
//    List<CheckMenuItem> categoryOptions = new ArrayList<>();
    public javafx.scene.control.ComboBox eventsMenu;
    public javafx.scene.control.Label lbl_user;
    public javafx.scene.control.Label lbl_org;
    public javafx.scene.control.Label lbl_rank;
    public javafx.scene.control.CheckBox finishedChoosing;
    public javafx.scene.control.CheckBox finishedChoosingEvent;
    public ArrayList<Integer> events = new ArrayList<>();
    public String uname;

    public void Init(String username,Controller controller, Stage stage){
        uname = username;
        this.controller = controller;
        this.stage = stage;
        User user = controller.getUser(username);
        if(!user.getOrganization().getName().equals("SD"))
            btn_Create.setDisable(true);
        lbl_user.setText("username: " + username);
        lbl_org.setText("Organization: " + user.getOrganization().getName());
        lbl_rank.setText("Rank: " + user.getRank());
        eventsMenu.setDisable(true);
        categoryOptions();
        final Tooltip tooltip = new Tooltip();
        tooltip.setText("you can open this combo box, \nsee all existing categories \nand select one");
        categoryMenu.setTooltip(tooltip);
        final Tooltip tooltip2 = new Tooltip();
        tooltip2.setText("you can open this combo box, \nsee all events belonging to your \nchosen category and choose one");
        eventsMenu.setTooltip(tooltip2);
        btn_Watch.setDisable(true);
        btn_CreateUpdate.setDisable(true);
    }
    @Override
    public void update(Observable o, Object arg) {

    }


    public void setMainStage(Stage stage) {
        this.MainStage = stage;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    };

    public void setController(Controller conection_layer){
        controller = conection_layer;
    }

    public void createEvent(){
        getStage().close();
        Stage stage = new Stage();
        stage.setTitle("Emer-Agency");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            Parent root = fxmlLoader.load(getClass().getResource("/create_event.fxml").openStream());
            Scene scene = new Scene(root, 754, 497);
            scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            CreateEventView cnv = fxmlLoader.getController();
            cnv.Init(uname, controller, stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void watch() throws ParseException {
        if(eventsMenu.getValue().equals("")) {
            showAlert("you must choose any event");
            return;
        }
        else {
            Event e = controller.watch(uname, (int) eventsMenu.getValue());
            if(e == null)
                showAlert("You don't have permission to view this event");
            else
                showAlert("id: " + e.getId() + "\ntitle: " + e.getTitle() + "\npublish date: " + e.getDate() +
                        "\nposted by: " + e.getPostedBy() + "\nfirst update: " + e.getFirstUpdate() + "\nstatus: " + e.getStatus());
        }

    }

    public void createUpdate(){
        getStage().close();
        Stage stage = new Stage();
        stage.setTitle("Emer-Agency");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            Parent root = fxmlLoader.load(getClass().getResource("/create_update.fxml").openStream());
            Scene scene = new Scene(root, 754, 497);
            scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            CreateUpdateView cuv = fxmlLoader.getController();
            int eventID = (int) eventsMenu.getValue();
            cuv.Init(uname, controller, stage, eventID);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void categoryOptions(){
        final Tooltip tooltip = new Tooltip();
        tooltip.setText("mark this check box \nonly after you have \nselected a category");
        finishedChoosing.setTooltip(tooltip);
        ArrayList<String> categories = controller.getCategories();
        categoryMenu.getItems().addAll(categories);
    }

    public void finishChoosingCategory(){
        eventsMenu.setDisable(false);
        events = new ArrayList<>();
        eventsMenu.getItems().removeAll();
        eventsOptions();
    }
    public void finishChoosingEvent(){
        btn_Watch.setDisable(false);
        btn_CreateUpdate.setDisable(false);
    }

    public void eventsOptions(){
        final Tooltip tooltip = new Tooltip();
        tooltip.setText("mark this check box only \nafter you have selected a \nevent and after this you \ncan watch or add update");
        finishedChoosingEvent.setTooltip(tooltip);
        if(categoryMenu.getValue() != ""){
            events = controller.getEvetnsByCategory((String) categoryMenu.getValue());
            eventsMenu.getItems().addAll(events);
        }
    }

    public Stage getStage() {
        return stage;
    }
}

