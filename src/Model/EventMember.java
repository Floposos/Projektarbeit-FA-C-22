package Model;

public class EventMember {
    private int eventMemberId;
    private int memberId;
    private int eventId;

    public EventMember(int eventMemberId, int memberId, int eventId) {

        this.eventMemberId = eventMemberId;
        this.memberId = memberId;
        this.eventId = eventId;

    }

    public int getEventMemberId() {
        return eventMemberId;
    }

    public void setEventMemberId(int eventMemberId) {
        this.eventMemberId = eventMemberId;
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