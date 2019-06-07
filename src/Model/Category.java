package Model;

public class Category {
    private String topic;
    private int id;

    public Category(int id, String topic) {
        this.topic = topic;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {

        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
