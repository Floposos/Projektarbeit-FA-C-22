package Model;

public class Result {
    private int resultId;
    private int participantId;
    private int eventId;
    private String resultDetails;

    public Result(int resultId, int participantId, int eventId, String resultDetails) {

        this.resultId = resultId;
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

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }
}
