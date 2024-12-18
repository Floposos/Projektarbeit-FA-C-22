package Model;

public class Result {
    private int participantId;
    private int eventId;
    private String resultDetails;

    public Result(int participantId, int eventId, String resultDetails) {
        this.participantId = participantId;
        this.eventId = eventId;
        this.resultDetails = resultDetails;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getResultDetails() {
        return resultDetails;
    }

    public void setResultDetails(String resultDetails) {
        this.resultDetails = resultDetails;
    }
}
