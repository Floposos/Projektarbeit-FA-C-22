package DatabaseOperations;

import Model.SportEvent;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SportEventDatabaseOperations {

    private static final String TABLE_NAME = "T_sport_events";

    public void insertSportEvent(int eventMemberId, int eventId, int sportId, LocalDate startDate, LocalDate endDate, List<String> resultValueList) {
        String query = "INSERT INTO " + TABLE_NAME + " (eventMemberId, eventId, sportId, startDate, endDate, resultValueList) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, eventMemberId);
            preparedStatement.setInt(2, eventId);
            preparedStatement.setInt(3, sportId);
            preparedStatement.setDate(4, Date.valueOf(startDate));
            preparedStatement.setDate(5, Date.valueOf(endDate));
            preparedStatement.setString(6, String.join(",", resultValueList));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Hinzufügen des SportEvents: " + e.getMessage(), e);
        }
    }

    public void updateSportEvent(int sportEventId, int eventMemberId, int eventId, int sportId, LocalDate startDate, LocalDate endDate, List<String> resultValueList) {
        String query = "UPDATE " + TABLE_NAME + " SET eventMemberId = ?, eventId = ?, sportId = ?, startDate = ?, endDate = ?, resultValueList = ? WHERE sportEventId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, eventMemberId);
            preparedStatement.setInt(2, eventId);
            preparedStatement.setInt(3, sportId);
            preparedStatement.setDate(4, Date.valueOf(startDate));
            preparedStatement.setDate(5, Date.valueOf(endDate));
            preparedStatement.setString(6, String.join(",", resultValueList));
            preparedStatement.setInt(7, sportEventId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Aktualisieren des SportEvents: " + e.getMessage(), e);
        }
    }

    public void deleteSportEvent(int sportEventId) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE sportEventId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sportEventId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen des SportEvents: " + e.getMessage(), e);
        }
    }

    public SportEvent getSportEventById(int sportEventId) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE sportEventId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sportEventId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String resultValueListString = resultSet.getString("resultValueList");
                    List<String> resultValueList = resultValueListString != null
                            ? Arrays.asList(resultValueListString.split(",")) : new ArrayList<>();
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
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen des SportEvents: " + e.getMessage(), e);
        }
        return null;
    }

    public int getEventIdByName(String eventName) {
        String query = "SELECT eventId FROM T_events WHERE eventName = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, eventName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("eventId");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der EventId: " + e.getMessage(), e);
        }
        return 0;
    }

    public List<SportEvent> getAllSportEvents() {
        List<SportEvent> sportEvents = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = DBConnection.Verbindung();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String resultValueListString = resultSet.getString("resultValueList");
                List<String> resultValueList = resultValueListString != null
                        ? Arrays.asList(resultValueListString.split(",")) : new ArrayList<>();
                SportEvent se = new SportEvent(
                        resultSet.getInt("sportEventId"),
                        resultSet.getInt("eventMemberId"),
                        resultSet.getInt("eventId"),
                        resultSet.getInt("sportId"),
                        resultSet.getDate("startDate").toLocalDate(),
                        resultSet.getDate("endDate").toLocalDate(),
                        resultValueList
                );
                sportEvents.add(se);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der SportEvents: " + e.getMessage(), e);
        }
        return sportEvents;
    }

}
