package Model;

public class Event {
    private int eventId;
    private String name;
    private String date;
    private String status;

    public Event(int eventId, String name, String date, String status) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.status = status;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
