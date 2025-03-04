package Model;

public class EventMember {
    private int eventMemberId;
    private int memberId;
    private int eventId;
    private int sportEventId;

    public EventMember(int eventMemberId, int memberId, int eventId, int sportEventId) {

        this.eventMemberId = eventMemberId;
        this.memberId = memberId;
        this.eventId = eventId;
        this.sportEventId = sportEventId;

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

    public int getSportEventId() {
        return sportEventId;
    }

    public void setSportEventId(int sportEventId) {
        this.sportEventId = sportEventId;
    }
}