package View;

import Controller.Controller;
import Model.Category;
import Model.Event;
import Model.SecurityForceUser;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateEventView extends MainView {

    public javafx.scene.control.TextField titleEvent;
    public javafx.scene.control.SplitMenuButton categoryMenu;
    public javafx.scene.control.DatePicker date;
    public javafx.scene.control.TextArea firstUpdate;
    public javafx.scene.control.ComboBox status;
    public javafx.scene.control.ComboBox FD;
    public javafx.scene.control.ComboBox PD;
    public javafx.scene.control.ComboBox MD;
    public javafx.scene.control.Button btn_create;
    public javafx.scene.control.Button btn_return;
    public javafx.scene.control.Label lbl_user;
    public javafx.scene.control.Label lbl_org;
    public javafx.scene.control.Label lbl_rank;
    ArrayList<CheckMenuItem> categoriesOption;
    public String uname;

    public void Init(String username, Controller controller, Stage stage){
        uname = username;
        this.controller = controller;
        this.stage = stage;
        User user = controller.getUser(username);
        lbl_user.setText("Username: " + username);
        lbl_org.setText("Organization: " + user.getOrganization().getName());
        lbl_rank.setText("Rank: " + user.getRank());
        fillStatus();
        fillCategories();
        fillUsers();
    }

    public void fillCategories(){
        categoriesOption = new ArrayList<>();
        for (String c : controller.getCategories() )
            categoriesOption.add(new CheckMenuItem(c));
        categoryMenu.getItems().addAll(categoriesOption);
    }

    public void fillUsers(){
        FD.getItems().addAll(controller.getUsersByOrg("FD"));
        PD.getItems().addAll(controller.getUsersByOrg("PD"));
        MD.getItems().addAll(controller.getUsersByOrg("MD"));

    }

    public void fillStatus(){
        status.getItems().add("IN_TREATMENT");
        status.getItems().add("FINISHED");
    }

    private boolean validation(){
        if(titleEvent.getText().equals("") || firstUpdate.getText().equals("")) {
            showAlert("You must fill the fields of title and firstUpdate");
            return false;
        }

        if(FD.getValue() == null && PD.getValue() == null && MD.getValue() == null){
            showAlert("You must choose at least one responsible user");
            return false;
        }

        if((status.getValue()) == null){
            showAlert("You must set the event status");
            return false;
        }

        boolean check = false;
        for (int i = 0; i < categoriesOption.size(); i++) {
            if(categoriesOption.get(i).isSelected()) {
                check = true;
                break;
            }
        }
        if(!check){
            showAlert("You must choose at least one category");
            return false;
        }

        return true;
    }

    public void create() throws ParseException {
        if(!validation())
            return;

        ArrayList<Category> categories= new ArrayList<>();
        for (int i = 0; i < categoriesOption.size(); i++) {
            if(categoriesOption.get(i).isSelected()) {
                categories.add(new Category(categoriesOption.get(i).getText()));
            }
        }
        String title = titleEvent.getText();
        LocalDate date = this.date.getValue();
        String firstUp = firstUpdate.getText();
        String status = (String)this.status.getValue();
        ArrayList<SecurityForceUser> responsibles = new ArrayList<>();
        if (FD.getValue() != null)
            responsibles.add((SecurityForceUser) controller.getUser((String) FD.getValue()));
        if (MD.getValue() != null)
            responsibles.add((SecurityForceUser)controller.getUser((String) MD.getValue()));
        if (PD.getValue() != null)
            responsibles.add((SecurityForceUser)controller.getUser((String) PD.getValue()));
        Event newEvent = new Event(title, categories, date, status, firstUp, responsibles);
        controller.createEvent(newEvent);
        showAlert("Event creation successful");
    }

    public void returnToMainWeb(){
        getStage().close();
        Stage stage = new Stage();
        stage.setTitle("Emer-Agency");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            Parent root = fxmlLoader.load(getClass().getResource("/website.fxml").openStream());
            Scene scene = new Scene(root, 754, 497);
            scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            WebsiteView website = fxmlLoader.getController();
            website.Init(uname, controller, stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
