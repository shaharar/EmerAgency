package DB;

import Model.*;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

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


    public ArrayList<Integer> getEventsByCategory (String categoryName) {
        ArrayList<Integer> events = new ArrayList<>();
        String sql = "SELECT eventId FROM CategoriesOfEvent WHERE category= '" + categoryName + "'";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                events.add(rs.getInt("eventId"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return events;
    }

    public Event getEvent (int eventID) throws ParseException {
        String sql = "SELECT * FROM Events WHERE id= " + eventID;
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            ArrayList<Category> categories = getCategoriesOfEvent(eventID);
            ArrayList<SecurityForceUser> responsibles = getResponsiblesOfEvent(eventID);
            Update firstUp = getUpdate(rs.getString("firstUpdate"));
            Event event = new Event(rs.getInt("id"), rs.getString("title"), categories, rs.getString("publishDate"),
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
            Update up = new Update(rs.getString("content"), rs.getInt("eventId"), rs.getString("username"), rs.getString("publishDate"));
            up.setOrderId(rs.getInt("orderId"));
            up.setLastUpdate(rs.getString("lastUpdate"));
            return up;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private ArrayList<SecurityForceUser> getResponsiblesOfEvent(int eventID) {
        ArrayList<SecurityForceUser> responsibleUsers = new ArrayList<>();
        String sql = "SELECT * FROM ResponsiblesOfEvent WHERE eventId= '" + eventID + "'";
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                responsibleUsers.add(new SecurityForceUser(rs.getString("username"),new Organization(rs.getString("organization")), rs.getInt("rank")));
            }
            return responsibleUsers;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private ArrayList<Category> getCategoriesOfEvent(int eventID) {
        ArrayList<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM CategoriesOfEvent WHERE eventId= '" + eventID + "'";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categories.add(new Category(rs.getString("category")));
            }
            return categories;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean addEvent(Event event) {
        String sql = "INSERT INTO Events(id,title,publishDate,postedBy,firstUpdate,status) VALUES(?,?,?,?,?,?)";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, event.getId());
            pstmt.setString(2, event.getTitle());
            pstmt.setString(3, event.getDate().toString());
            pstmt.setString(4, event.getPostedBy());
            pstmt.setString(5, event.getFirstUpdate().getContent());
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
            String sql = "INSERT INTO CategoriesOfEvent(eventId,category) VALUES(?,?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, event.getId());
                pstmt.setString(2, cat.getTopic());
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
            String sql = "INSERT INTO ResponsiblesOfEvent(eventId,username,organization) VALUES(?,?,?)";
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

    public ArrayList<Permission> getPermissionsOfEvent (String username, int eventID) {
        ArrayList<Permission> permissions = new ArrayList<>();
        String sql = "SELECT * FROM Permissions WHERE username= '" + username + "' and eventId= '" + eventID + "'";
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                permissions.add(new Permission(rs.getString("username"),rs.getInt("eventId"),rs.getString("permission")));
            }
            return permissions;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean addUpdate(Update update) {
        String sql = "INSERT INTO Updates(content,username,eventId,orderId,publishDate,lastUpdate) VALUES(?,?,?,?,?,?)";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, update.getContent());
            pstmt.setString(2, update.getUsername());
            pstmt.setInt(3, update.getEventId());
            pstmt.setInt(4, (update.getOrderId()));
            pstmt.setString(5, update.getPublishDate().toString());
            pstmt.setString(6, update.getLastUpdate().getContent());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public String[] getLastUpdate(int eventId) {
        String[] updateInfo = new String[2];
        String sql = "SELECT MAX(orderId) as maxOrder FROM Updates WHERE eventId= '" + eventId + "'";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            updateInfo[0] = "" + rs.getInt("maxOrder");
            updateInfo[1] = rs.getString("content");
            return updateInfo;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public Category getCategory (String topic){
        String sql = "SELECT * FROM Categories WHERE name= '" + topic + "'";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            Category c = new Category(rs.getString("name"));
            return c;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean login(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username= '" + username + "'";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if(rs.next()){
                if(password.equals(rs.getString("password"))){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public User getUser(String username) {
        String sql = "SELECT * FROM Users WHERE username= '" + username + "'";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            Organization organization = new Organization(rs.getString("organization"));
            User user;
            if(organization.getName().equals("service center"))
                user = new ServiceCenterUser(rs.getString("username"), organization, rs.getInt("rank"));
            else
                user = new RegularUser(rs.getString("username"), organization, rs.getInt("rank"));
           return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String getOrganizationOfUser(String username) {
        String sql = "SELECT organization FROM Users WHERE username= '" + username + "'";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.getString("organization");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public int getRankOfUser(String username) {
        String sql = "SELECT rank FROM Users WHERE username= '" + username + "'";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.getInt("rank");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
