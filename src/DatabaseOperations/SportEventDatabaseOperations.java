package DatabaseOperations;


import Model.Sport;

import java.time.LocalDate;
import java.util.List;

public class SportEventDatabaseOperations {

    private static final String TABLE_NAME = "T_sport_events";
    public void insertSportEvent(LocalDate startDate, LocalDate endDate, List<String> resultValueList) {
        // Database query to insert a participant
        String query = "INSERT INTO " + TABLE_NAME + "("
    }

    public void deleteSportEvent(int id) {
        // Database query to delete a participant
    }

    public Sport getSportEventById(int id) {
        // Database query to retrieve a participant by ID
        return null;
    }
}
