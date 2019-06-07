package DB;

import Model.*;

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
            ArrayList<Category> categories = getCategoriesOfEvent(eventID);
            ArrayList<SecurityForceUser> responsibles = getResponsiblesOfEvent(eventID);
            Update firstUp = getUpdate(rs.getString("firstUpdate"));
            Event event = new Event(rs.getInt("id"), rs.getString("title"), categories, rs.getString("date"),
                    rs.getString("postedBy"), firstUp, rs.getString("status"), responsibles);
            return event;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Update getUpdate(String firstUpdate) {
        String sql = "SELECT * FROM Updates WHERE content= '" + firstUpdate + "'";
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            Update up = new Update(rs.getInt("id"), rs.getString("content"), rs.getInt("eventId"), rs.getString("username"),rs.getInt("orderId"), rs.getString("publishDate"));
            return up;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private ArrayList<SecurityForceUser> getResponsiblesOfEvent(int eventID) {
        return new ArrayList<>();
    }

    private ArrayList<Category> getCategoriesOfEvent(int eventID) {
        return new ArrayList<>();
    }

    public boolean addEvent(Event event) {
        String sql = "INSERT INTO Events(eventId,title,publishDate,postedBy,firstUpdate,status) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, event.getId());
            pstmt.setString(2, event.getTitle());
            pstmt.setDate(3, (java.sql.Date) event.getDate());
            pstmt.setString(4, event.getPostedBy());
            pstmt.setInt(5, event.getFirstUpdate().getID());
            pstmt.setString(6, event.getStatus().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean addCategoriesOfEvent(Event event) {
        for (Category cat : event.getCategories()) {
            String sql = "INSERT INTO CategoriesOfEvent(eventId,categoryId) VALUES(?,?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, event.getId());
                pstmt.setInt(2, cat.getId());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    public boolean addResponsiblesOfEvent(Event event) {
        for (SecurityForceUser sfUser : event.getResponsibleUsers()) {
            String sql = "INSERT INTO ResponsiblessOfEvent(eventId,username,organization) VALUES(?,?,?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, event.getId());
                pstmt.setString(2, sfUser.getUsername());
                pstmt.setString(3, sfUser.getOrganization().getName());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

//    public String getPermissionOfEvent (String username, String eventID) {
//
//    }
//
//    public String createUpdate (String username, String eventInfo, Date updateDate) {
//
//    }


}
