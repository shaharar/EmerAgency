package View;

import Controller.Controller;
import Model.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

public class CreateUpdateView extends MainView {

    public javafx.scene.control.TextArea updateContent;
    public javafx.scene.control.Button btn_create;
    public javafx.scene.control.DatePicker date;
    public javafx.scene.control.Button btn_return;
    public javafx.scene.control.Label lbl_user;
    public javafx.scene.control.Label lbl_org;
    public javafx.scene.control.Label lbl_rank;
    public javafx.scene.control.Label lbl_event;
    public String uname;
    private int eventID;

    public void Init(String username, Controller controller, Stage stage, int eventId){
        uname = username;
        this.controller = controller;
        this.stage = stage;
        User user = controller.getUser(username);
        lbl_user.setText("Username: " + username);
        lbl_org.setText("Organization: " + user.getOrganization().getName());
        lbl_rank.setText("Rank: " + user.getRank());
        lbl_event.setText("Event Id:     " + eventId);
        this.eventID = eventId;
    }

    public void createUpdate() {

        if(updateContent.getText().equals("")) {
            showAlert("You must fill the field of content");
            return;
        }

        LocalDate date = this.date.getValue();
        String content = updateContent.getText();
        Update newUpdate = new Update(content,eventID,uname,date);
        controller.createUpdate(newUpdate);
        showAlert("Update creation successful");
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
