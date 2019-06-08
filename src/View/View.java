package View;

import Controller.Controller;
import Model.Organization;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;

public class View implements Observer {

    public javafx.scene.control.Button btn_Create;
    public javafx.scene.control.Button btn_Watch;
    public javafx.scene.control.Button btn_Edit;
    public javafx.scene.control.TextField titleEvent;
    public javafx.scene.control.TextField firstUpdate;
    public javafx.scene.control.TextField eventIdToWatch;
    public javafx.scene.control.TextField eventIdToEdit;
    public javafx.scene.control.TextField updateId;
    public javafx.scene.control.TextField newContent;
    public SplitMenuButton categoryMenu;
    List<CheckMenuItem> categoryOptions = new ArrayList<>();
    public javafx.scene.control.Label lbl_user;
    public javafx.scene.control.Label lbl_org;
    public javafx.scene.control.Label lbl_rank;

    static Controller controller;
    static Stage MainStage;
    Stage stage;
    private String username;
    private String password;

    public void Init(String username, Stage stage){
        controller = new Controller();
        this.stage = stage;
        User user = controller.getUser(username);
        lbl_user.setText("username: " + username);
        lbl_org.setText("Organization: " + user.getOrganization().getName());
        lbl_rank.setText("Rank: " + user.getRank());
    }
    @Override
    public void update(Observable o, Object arg) {

    }

    public void setMainStage(Stage stage) {
        this.MainStage = stage;
    }

    public void setStage(Stage stage){
        this.stage=stage;
    };

    public void setController(Controller controller){
        this.controller = controller;
        this.categoryOptions(controller.getCategories());
    }

    public void ChangeScene(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getClassLoader().getResource(fxml).openStream());
            Scene scene = new Scene(root, 1024, 600);
            MainStage.setScene(scene);
            if(fxml.equals("website.fxml")){
                //   scene.getStylesheets().add(getClass().getClassLoader().getResource("Background.css").toExternalForm());
            }else if(fxml.equals("login.fxml")){
                // scene.getStylesheets().add(getClass().getClassLoader().getResource("ViewStyle.css").toExternalForm());
            }
            MainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertMessage);
        alert.show();
    }

    public void create(){
        if(titleEvent.getText().equals("") || firstUpdate.getText().equals("")) {
            showAlert("you must fill the fields of title and firstUpdate");
            return;
        }
        boolean check=false;
        for (int i = 0; i < categoryOptions.size(); i++) {
            if(categoryOptions.get(i).isSelected()) {
                check = true;
                break;
            }
        }
        if(!check){
            showAlert("you must choose at least one category");
            return;
        }

        controller.create(titleEvent.getText(), firstUpdate.getText());
        showAlert("event was created");
    }


    public void watch(){
        if(eventIdToWatch.getText().equals("") ) {
            showAlert("you must fill the field of eventId");
            return;
        }
        controller.watch(eventIdToWatch.getText());
       //מה זה אמור להציג?
    }


    public void edit(){
        if(eventIdToEdit.getText().equals("") || updateId.getText().equals("") || newContent.getText().equals("")) {
            showAlert("you must fill the fields of eventId, updateId and newContent");
            return;
        }
        controller.edit(titleEvent.getText(), firstUpdate.getText(), newContent.getText());
        showAlert("edit was successful");
    }
    public void categoryOptions(HashSet<String> categories){
        categoryOptions = new ArrayList<>();
        for (String category : categories)
            categoryOptions.add(new CheckMenuItem(category));
        categoryMenu.getItems().addAll(categoryOptions);
    }


    public void closeWindow(){
        stage.close();
    }
}

