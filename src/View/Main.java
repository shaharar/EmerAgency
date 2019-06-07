package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Controller.Controller;

public class Main extends Application {

    private WebsiteView website;
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            primaryStage.setTitle("Welcome to EmerAgency");
            Parent root = fxmlLoader.load(getClass().getResource("website.fxml").openStream());
            Scene scene = new Scene(root, 700.0, 700.0);
            primaryStage.setScene(scene);
            website = fxmlLoader.getController();
            website.setMainStage(primaryStage);
            Controller con = new Controller(website);
            View Main_control = fxmlLoader.getController();
            Main_control.setController(con);
            primaryStage.show();

        }
        catch (Exception e){

        }

    }


    public static void main(String[] args) {
        launch(args);
    }

}
