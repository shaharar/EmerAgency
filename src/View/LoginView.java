package View;

import Controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView {

    public javafx.scene.control.Button btn_login;
    public javafx.scene.control.TextField userId;
    public PasswordField password = new PasswordField();

    static Controller controller;
    static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {

        LoginView.stage = stage;
    }

    public void setController(Controller conection_layer) {
        controller = conection_layer;
    }

    private void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertMessage);
        alert.show();
    }

    public void init(){
        userId.setPromptText("Username");
        password.setPromptText("Password");
    }

    public void login () {
        if (!controller.login(userId.getText(), password.getText())) {
            showAlert("Incorrect details");
        } else {
            controller.login(userId.getText(), password.getText());
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
                website.Init(userId.getText(), controller, stage);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
