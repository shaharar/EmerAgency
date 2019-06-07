package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Update {

    private int ID;
    private String content;
    private int eventId;
    private String username;
    private int orderId;
    private Date publishDate;

    public Update(int ID, String content, int eventId, String username, int orderId, String publishDate) {
        this.ID = ID;
        this.content = content;
        this.eventId = eventId;
        this.username = username;
        this.orderId = orderId;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date d = null;
        try {
            d = formatter.parse(publishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.publishDate = d;
    }

    public int getID() {
        return this.ID;
    }

    public String getContent() {
        return content;
    }

    public int getEventId() {
        return eventId;
    }

    public String getUsername() {
        return username;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getPublishDate() {
        return publishDate;
    }
}
