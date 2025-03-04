package Logic;

import DatabaseOperations.SportEventDatabaseOperations;
import DatabaseOperations.EventDatabaseOperations;
import DatabaseOperations.SportDatabaseOperations;
import Model.SportEvent;

import java.time.LocalDate;
import java.util.List;

public class SportEventManager {

    private SportEventDatabaseOperations sportEvDataOP;
    private EventDatabaseOperations evDataOP;
    private SportDatabaseOperations sportDataOP;


    public SportEventManager() {

        this.sportEvDataOP = new SportEventDatabaseOperations();
        this.evDataOP = new EventDatabaseOperations();
        this.sportDataOP = new SportDatabaseOperations();
    }

//    public void addSportEvent(int eventMemberId, int eventId, int sportId, LocalDate startDate, LocalDate endDate, List<String> resultValueList) {
public void addSportEvent(int eventMemberId, int eventId, int sportId, LocalDate startDate, LocalDate endDate, String resultValueList) {
        if (eventId <= 0 || sportId <= 0 || startDate == null || endDate == null || resultValueList == null) {
            throw new IllegalArgumentException("Ungültige Eingaben für das SportEvent.");
        }
        sportEvDataOP.insertSportEvent(eventMemberId, eventId, sportId, startDate, endDate, resultValueList);
    }

    public void addSportEvent(int eventId, int sportId, LocalDate startDate, LocalDate endDate) {
        if (eventId <= 0 || sportId <= 0 || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Ungültige Eingaben für das SportEvent.");
        }
        sportEvDataOP.insertSportEvent(0, eventId, sportId, startDate, endDate, null);
    }

//    public void addSportEvent(String eventName, String sportName, LocalDate startDate, LocalDate endDate) {
//        int eventId = evDataOP.getEventIDByName(eventName);
//
//        int sportId = sportDataOP.getSportIdByName(sportName);
//
//        if (startDate == null || endDate == null) {
//            throw new IllegalArgumentException("Ungültige Eingaben für das SportEvent.");
//        }
//        sportEvDataOP.insertSportEvent(0, eventId, sportId, startDate, endDate, null);
//    }


//    public void updateSportEvent(int sportEventId, int eventMemberId, int eventId, int sportId, LocalDate startDate, LocalDate endDate, List<String> resultValueList) {
public void updateSportEvent(int sportEventId, int eventMemberId, int eventId, int sportId, LocalDate startDate, LocalDate endDate, String resultValueList) {

        if (sportEventId <= 0 || eventMemberId <= 0 || eventId <= 0 || sportId <= 0 || startDate == null || endDate == null || resultValueList == null) {
            throw new IllegalArgumentException("Ungültige Eingaben für die Aktualisierung des SportEvents.");
        }
        sportEvDataOP.updateSportEvent(sportEventId, eventMemberId, eventId, sportId, startDate, endDate, resultValueList);
    }

    public void deleteSportEvent(int sportEventId) {
        if (sportEventId <= 0) {
            throw new IllegalArgumentException("Ungültige SportEvent-ID.");
        }
        sportEvDataOP.deleteSportEvent(sportEventId);
    }

    public SportEvent getSportEventById(int sportEventId) {
        if (sportEventId <= 0) {
            throw new IllegalArgumentException("Ungültige SportEvent-ID.");
        }
        return sportEvDataOP.getSportEventById(sportEventId);
    }

//    public int getSportEventIdByName(String eventName) {
//        if (eventName == null) {
//            throw new IllegalArgumentException("Ungültige SportEvent-ID.");
//        }
//        return sportEvDataOP.getEventIdByName(eventName);
//    }

    public List<SportEvent> getAllSportEvents() {
        return sportEvDataOP.getAllSportEvents();
    }
}
