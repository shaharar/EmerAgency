package Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Event {
    private int id;
    private String title;
    private ArrayList<Category> categories;
    private LocalDate date;
    private String postedBy;
    private String firstUpdate;
    private EventStatus status;
    private ArrayList<SecurityForceUser> responsibleUsers;

    private Permission[] permissions;

    public Event(String title, ArrayList<Category> categories, LocalDate date, String status, String firstUpdate, ArrayList<SecurityForceUser> responsibleUsers) throws ParseException {
        this.title = title;
        this.categories = categories;
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //Date d = formatter.parse(date);
        this.date = date;
        this.postedBy = "";
        this.firstUpdate = firstUpdate;
        this.status = EventStatus.valueOf(status);
        this.responsibleUsers = responsibleUsers;
    }

    public Event(int id, String title, ArrayList<Category> categories, String date, String postedBy, String firstUp, String status, ArrayList<SecurityForceUser> responsibles) throws ParseException {
        this.id = id;
        this.title = title;
        this.categories = categories;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //LocalDate d = formatter.format(date);
        this.date = LocalDate.parse(date, formatter);
        this.postedBy = postedBy;
        this.firstUpdate = firstUp;
        this.status = EventStatus.valueOf(status);
        this.responsibleUsers = responsibles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getFirstUpdate() {
        return firstUpdate;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public ArrayList<SecurityForceUser> getResponsibleUsers() {
        return responsibleUsers;
    }

    public Permission[] getPermissions() {
        return permissions;
    }
}
