package Model;
import java.time.LocalDate;
import java.util.List;

public class SportEvent {
    private int sportEventId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> resultValueList;
    private int eventMemberId;
    private int eventId;
    private int sportId;

    public SportEvent(int sportEventId, int eventId, int sportId, int eventMemberId, LocalDate startDate, LocalDate endDate, List<String> resultValueList) {
        this.sportEventId = sportEventId;
        this.eventId = eventId;
        this.sportId = sportId;
        this.eventMemberId = eventMemberId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.resultValueList = resultValueList;
    }

    public int getSportEventId() {
        return sportEventId;
    }

    public void setSportEventId(int sportEventId) {
        this.sportEventId = sportEventId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<String> getResultValueList() {
        return resultValueList;
    }

    public void setResultValueList(List<String> resultValueList) {
        this.resultValueList = resultValueList;
    }

    public int getEventMemberId() {
        return eventMemberId;
    }

    public void setEventMemberId(int eventMemberId) {
        this.eventMemberId = eventMemberId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }
}
