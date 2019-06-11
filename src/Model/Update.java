package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Update {

    private String content;
    private int eventId;
    private String username;
    private int orderId;
    private LocalDate publishDate;
    private Update lastUpdate;

    public Update(String content, int eventId, String username, LocalDate publishDate) {
        this.content = content;
        this.eventId = eventId;
        this.username = username;
        this.orderId = 1;
/*        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date d = null;
        try {
            d = formatter.parse(publishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.publishDate = d;*/
        this.publishDate = publishDate;
        lastUpdate = new Update();
    }

    public Update() {
    }

    public Update(String content) {
        this.content = content;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate.setContent(lastUpdate);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Update getLastUpdate() {
        return lastUpdate;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public LocalDate getPublishDate() {
        return publishDate;
    }
}
