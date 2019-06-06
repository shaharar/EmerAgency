package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DBManager {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:resources\\db.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;


    }

    public ArrayList<String> getCategories() {
        ArrayList<String> allCategories = new ArrayList<>();
        String sql = "SELECT name FROM Categories";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                allCategories.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allCategories;
    }

    public ArrayList<String> getUsersByOrganization(String organization) {
        ArrayList<String> users = new ArrayList<>();
        String sql = "SELECT userId FROM Users WHERE organization= '" + organization + "'";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                users.add(rs.getString("userId"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;

    }

//    public void createEvent(String username, String title, Date publishDate, int updateID) {
//
//    }
//
//    public String getFirstUpdate() {
//
//    }
//
    public ArrayList<String> getEventsByCategory (String categoryName) {
        ArrayList<String> events = new ArrayList<>();
        String sql = "SELECT id FROM Events WHERE category= '" + categoryName + "'";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                events.add(rs.getString("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return events;
    }
//
//    public String[] getEvent (String eventID) {
//
//    }
//
//    public String getPermissionOfEvent (String username, String eventID) {
//
//    }
//
//    public String createUpdate (String username, String eventInfo, Date updateDate) {
//
//    }


}
