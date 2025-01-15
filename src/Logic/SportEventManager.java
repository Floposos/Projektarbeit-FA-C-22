package Logic;

import DatabaseOperations.SportEventDatabaseOperations;

import java.time.LocalDate;
import java.util.List;

public class SportEventManager {

    private SportEventDatabaseOperations sportEvDataOP;

    public SportEventManager() {
        this.sportEvDataOP = new SportEventDatabaseOperations();
    }

    public void addSportEvent(int sportEventId, int eventMemberId, int eventId, int sportId, LocalDate startDate, LocalDate endDate, List<String> resultValueList) {

    }
}
