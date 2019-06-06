package Model;

import DB.DBManager;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.Date;

public class Model implements Observable {

    private DBManager dbManager;

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }


/*    public ArrayList<String> getAllCategories() {
        ArrayList<String> categories = new ArrayList<>();
        categories.addAll(dbManager.getCategories());
        return categories;
    }

    public ArrayList<String> getPolicemen() {

    }

    public ArrayList<String> getFiremen() {

    }

    public ArrayList<String> getParamedics() {

    }

    public void createEvent(String username, String title, Date publishDate, int updateID) {

    }

    public String getFirstUpdate() {

    }

    public String[] getEventsByCategory (String categoryName) {

    }

    public String[] getEvent (String eventID) {

    }

    public String getPermissionOfEvent (String username, String eventID) {

    }

    public String createUpdate (String username, String eventInfo, Date updateDate) {

    }*/
}
