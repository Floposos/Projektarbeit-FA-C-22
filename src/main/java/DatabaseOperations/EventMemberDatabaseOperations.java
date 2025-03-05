package DatabaseOperations;

import Model.EventMember;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventMemberDatabaseOperations {

    private static final String TABLE_NAME = "T_eventMember";
    private final EventDatabaseOperations eventDb;
    private final SportEventDatabaseOperations sportEventDb;

    public EventMemberDatabaseOperations() {
        this.eventDb = new EventDatabaseOperations();
        this.sportEventDb = new SportEventDatabaseOperations();
    }

    public void insertEventMember(int memberId, int eventId, int sportEventId) {
        if (!eventDb.eventExists(eventId)) {
            throw new IllegalArgumentException("Event mit ID " + eventId + " existiert nicht.");
        }
        if (!sportEventDb.sportEventExists(sportEventId)) {
            throw new IllegalArgumentException("SportEvent mit ID " + sportEventId + " existiert nicht.");
        }
        if (isMemberAlreadyRegistered(memberId, eventId)) {
            throw new IllegalArgumentException("Mitglied ist bereits für dieses Event angemeldet.");
        }

        String query = "INSERT INTO " + TABLE_NAME + " (memberId, eventId, sportEventId) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, memberId);
            preparedStatement.setInt(2, eventId);
            preparedStatement.setInt(3, sportEventId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Hinzufügen des EventMembers: " + e.getMessage(), e);
        }
    }

    public boolean isMemberAlreadyRegistered(int memberId, int eventId) {
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE memberId = ? AND eventId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            preparedStatement.setInt(2, eventId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Überprüfen der Mitgliedschaft: " + e.getMessage(), e);
        }
        return false;
    }

    public int getEventMemberId(int memberId, int eventId) {
        String query = "SELECT eventMemberId FROM " + TABLE_NAME + " WHERE memberId = ? AND eventId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            preparedStatement.setInt(2, eventId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("eventMemberId");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der EventMember-ID: " + e.getMessage(), e);
        }
        return -1;
    }
    public void deleteEventMember(int memberId, int eventId) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE memberId = ? AND eventId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, memberId);
            preparedStatement.setInt(2, eventId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Kein Eintrag zum Löschen gefunden.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen des EventMembers: " + e.getMessage(), e);
        }
    }

    public List<EventMember> getAllEventMembers() {
        List<EventMember> eventMembers = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = DBConnection.Verbindung();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                EventMember em = new EventMember(
                        resultSet.getInt("eventMemberId"),
                        resultSet.getInt("memberId"),
                        resultSet.getInt("eventId"),
                        resultSet.getInt("sportEventId")
                );
                eventMembers.add(em);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der EventMembers: " + e.getMessage(), e);
        }
        return eventMembers;
    }
}
