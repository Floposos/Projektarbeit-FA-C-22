package Model;

public class Event {

    private int eventId;
    private int administratorId;
    private String name;
    private String status;

    public Event(int eventId, int administratorId, String name, String status) {

        this.eventId = eventId;
        this.administratorId = administratorId;
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(int administratorId) {
        this.administratorId = administratorId;
    }
}
