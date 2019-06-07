package Controller;

import Model.Model;
import View.View;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Observer;

public class Controller implements Observable, Observer {

    private Model model;

    public HashSet<String> categories;


    public Controller(View view) {
        this.model = new Model(this);
    }

    public HashSet<String> getCategories() {
        return categories;
    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }

    public void openwindow(String fxmlfile, Object Parameter) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getClassLoader().getResource(fxmlfile).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage newStage = new Stage();
        Scene scene = new Scene(root, 600, 457);
        newStage.setScene(scene);

        View NewWindow = fxmlLoader.getController();
        // NewWindow.setStage(newStage);
        NewWindow.setController(this);
        newStage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
        newStage.show();
    }
    public boolean login(String username, String password) {
        return  (model.login(username, password));
    }

    public void create(String title, String firstUpdate){

    }
    public void watch(String eventId){

    }
    public void edit(String eventId, String updateId, String newContent){

    }

}
