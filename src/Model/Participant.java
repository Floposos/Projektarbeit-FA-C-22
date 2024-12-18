package Model;

public class Participant {
    private int participantId;
    private int memberId;
    private int eventId;

    public Participant(int participantId, int memberId, int eventId) {
        this.participantId = participantId;
        this.memberId = memberId;
        this.eventId = eventId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
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