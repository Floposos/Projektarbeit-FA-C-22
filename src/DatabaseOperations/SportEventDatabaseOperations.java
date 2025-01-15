package DatabaseOperations;

import DatabaseOperations.DBConnection;
import Model.Club;
import Model.Sport;
import Model.SportEvent;
import org.sqlite.core.DB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SportEventDatabaseOperations {

    private static final String TABLE_NAME = "T_sport_events";
    public void insertSportEvent(int sportEventId, LocalDate startDate, LocalDate endDate, List<String> resultValueList, int eventMemberId, int eventId, int sportId) {
        // Database query to insert a participant
        String query = "INSERT INTO " + TABLE_NAME + "(startDate, endDate, resultValueList, eventMemberId, eventId, sportId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseOperations.DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sportEventId);
            preparedStatement.setDate(2, Date.valueOf(startDate));
            preparedStatement.setDate(3, Date.valueOf(endDate));
            preparedStatement.setString(4, String.valueOf(resultValueList));
            preparedStatement.setInt(5, eventMemberId);
            preparedStatement.setInt(6, eventId);
            preparedStatement.setInt(7, sportId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateSportEvent(int sportEventId, LocalDate startDate, LocalDate endDate, List<String> resultValueList, int eventMemberId, int eventId, int sportId){
        String query = "UPDATE " + TABLE_NAME + "SET sportEvent = ?, startDate = ?, endDate = ?, resultValueList, eventMemberId = ?, eventId = ?, sportId = ? WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sportEventId);
            preparedStatement.setDate(2, Date.valueOf(startDate));
            preparedStatement.setDate(3, Date.valueOf(endDate));
            preparedStatement.setString(4, String.valueOf(resultValueList));
            preparedStatement.setInt(5, eventMemberId);
            preparedStatement.setInt(6, eventId);
            preparedStatement.setInt(7, sportId);
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim aktualisieren des Events: " + e.getMessage());
        }
    }

    public void deleteSportEvent(int id) throws SQLException {
        // Database query to delete a participant
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            throw new RuntimeException("Fehler bei Löschung des Events: " + e.getMessage());
        }
    }

    public SportEvent getSportEventById(int id) throws SQLException {
        // Database query to retrieve a participant by ID
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String resultValueListString = resultSet.getString("resultValueList");
                    List<String> resultValueList  = resultValueListString != null
                            ? Arrays.asList(resultValueListString.split(","))
                            : new ArrayList<>();
                    return new SportEvent(
                            resultSet.getInt("sportEventId"),
                            resultSet.getInt("eventId"),
                            resultSet.getInt("sportId"),
                            resultSet.getInt("eventMemberId"),
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