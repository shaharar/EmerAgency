package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Controller.Controller;
import Model.Model;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Model model = new Model();
        Controller controller = new Controller();
        controller.setModel(model);
        FXMLLoader fxm = new FXMLLoader();
        Parent root = fxm.load(getClass().getResource("/login.fxml").openStream());
        primaryStage.setTitle("Emer-Agency");
        Scene scene = new Scene(root, 600, 392);
        scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        LoginView Main_control = fxm.getController();
        Main_control.setController(controller);
        Main_control.setStage(primaryStage);
        Main_control.init();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
