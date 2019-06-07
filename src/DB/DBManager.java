package DB;

import Model.Category;
import Model.Event;
import Model.User;

import java.sql.*;
import java.text.ParseException;
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
        String sql = "SELECT username FROM Users WHERE organization= '" + organization + "'";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                users.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

//    public User getUserInfo (String userId){
//
//        return null;
//    }

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
    public Category getCategory (String topic){
        String sql = "SELECT * FROM Categories WHERE name= '" + topic + "'";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            Category c = new Category(rs.getInt("id"), rs.getString("name"));
            return c;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Event getEvent (int eventID) throws ParseException {
        String sql = "SELECT * FROM Events WHERE id= " + eventID;
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            Category c = getCategory(rs.getString("category"));
            Event event = new Event(rs.getInt("id"), rs.getString("title"), c,
                     rs.getString("date"), rs.getString("postedBy"),
                     rs.getString("firstUpdate"), rs.getString("status"));
            return event;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

//    public String getPermissionOfEvent (String username, String eventID) {
//
//    }
//
//    public String createUpdate (String username, String eventInfo, Date updateDate) {
//
//    }


}
