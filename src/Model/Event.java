package Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Event {
    private int id;
    private String title;
    private Category category;
    private Date date;
    private String postedBy;
    private String firstUpdate;
    private String status; //need change this to enam!

    public Event(int id, String title, Category category, String date, String postedBy, String firstUpdate, String status) throws ParseException {
        this.id = id;
        this.title = title;
        this.category = category;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date d = formatter.parse(date);
        this.date = d;
        this.postedBy = postedBy;
        this.firstUpdate = firstUpdate;
        this.status = status;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getFirstUpdate() {
        return firstUpdate;
    }

    public void setFirstUpdate(String firstUpdate) {
        this.firstUpdate = firstUpdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
