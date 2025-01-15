package DatabaseOperations;

import Model.SportEvent;
import org.sqlite.core.DB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SportEventDatabaseOperations {

    private static final String TABLE_NAME = "T_sport_events";
    public void insertSportEvent(int eventMemberId, int eventId, int sportId, LocalDate startDate, LocalDate endDate, List<String> resultValueList) {
        // Database query to insert a participant
        String query = "INSERT INTO " + TABLE_NAME + "(eventMemberId, eventId, sportId, startDate, endDate, resultValueList) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, eventMemberId);
            preparedStatement.setInt(2, eventId);
            preparedStatement.setInt(3, sportId);
            preparedStatement.setDate(4, Date.valueOf(startDate));
            preparedStatement.setDate(5, Date.valueOf(endDate));
            preparedStatement.setString(6, String.valueOf(resultValueList));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateSportEvent(int sportEventId, int eventMemberId, int eventId, int sportId, LocalDate startDate, LocalDate endDate, List<String> resultValueList){
        String query = "UPDATE " + TABLE_NAME + "SET eventMemberId = ?, eventId = ?, sportId = ?, startDate = ?, endDate = ?, resultValueList WHERE sportEventId = ?";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sportEventId);
            preparedStatement.setInt(2, eventMemberId);
            preparedStatement.setInt(3, eventId);
            preparedStatement.setInt(4, sportId);
            preparedStatement.setDate(5, Date.valueOf(startDate));
            preparedStatement.setDate(6, Date.valueOf(endDate));
            preparedStatement.setString(7, String.valueOf(resultValueList));
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim aktualisieren des Events: " + e.getMessage());
        }
    }

    public void deleteSportEvent(int sportEventId) throws SQLException {
        // Database query to delete a participant
        String query = "DELETE FROM " + TABLE_NAME + " WHERE sportEventId = ?";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sportEventId);
        } catch (SQLException e) {
            throw new RuntimeException("Fehler bei Löschung des Events: " + e.getMessage());
        }
    }

    public SportEvent getSportEventById(int sportEventId) throws SQLException {
        // Database query to retrieve a participant by ID
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE sportEventId = ?";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sportEventId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String resultValueListString = resultSet.getString("resultValueList");
                    List<String> resultValueList  = resultValueListString != null
                            ? Arrays.asList(resultValueListString.split(","))
                            : new ArrayList<>();
                    return new SportEvent(
                            resultSet.getInt("sportEventId"),
                            resultSet.getInt("eventMemberId"),
                            resultSet.getInt("eventId"),
                            resultSet.getInt("sportId"),
                            resultSet.getDate("startDate").toLocalDate(),
                            resultSet.getDate("endDate").toLocalDate(),
                            resultValueList
                    );
                }
            }
        }
        return null;
    }
}
// int sportEvent, LocalDate startDate, LocalDate endDate, List<String> resultValueList