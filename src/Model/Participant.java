package Model;

public class Participant {
    private int id;
    private int memberId;
    private int eventId;

    public Participant(int id, int memberId, int eventId) {
        this.id = id;
        this.memberId = memberId;
        this.eventId = eventId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}