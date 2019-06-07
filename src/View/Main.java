package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Controller.Controller;

public class Main extends Application {

    private View view;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("website.fxml").openStream());
        primaryStage.setTitle("Welcome to EmerAgency");
        Scene scene = new Scene(root, 640, 480);
        primaryStage.setScene(scene);
        view = fxmlLoader.getController();
        view.setMainStage(primaryStage);
        Controller con = new Controller(view);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
