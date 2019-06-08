package Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Event {
    private int id;
    private String title;
    private ArrayList<Category> categories;
    private Date date;
    private String postedBy;
    private Update firstUpdate;
    private EventStatus status;
    private ArrayList<SecurityForceUser> responsibleUsers;

    private Permission[] permissions;

    public Event(String title, ArrayList<Category> categories, String date, Update firstUpdate, ArrayList<SecurityForceUser> responsibleUsers) throws ParseException {
        this.title = title;
        this.categories = categories;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date d = formatter.parse(date);
        this.date = d;
        this.postedBy = postedBy;
        this.firstUpdate = firstUpdate;
        this.status = EventStatus.IN_TREATMENT;
        this.responsibleUsers = responsibleUsers;
    }

    public Event(int id, String title, ArrayList<Category> categories, String date, String postedBy, Update firstUp, String status, ArrayList<SecurityForceUser> responsibles) throws ParseException {
        this.id = id;
        this.title = title;
        this.categories = categories;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date d = formatter.parse(date);
        this.date = d;
        this.postedBy = postedBy;
        this.firstUpdate = firstUpdate;
        this.status = EventStatus.valueOf(status);
        this.responsibleUsers = responsibleUsers;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public Update getFirstUpdate() {
        return firstUpdate;
    }

    public void setFirstUpdate(Update firstUpdate) {
        this.firstUpdate = firstUpdate;
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
