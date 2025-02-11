package DatabaseOperations;

import Model.EventMember;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventMemberDatabaseOperations {

    private static final String TABLE_NAME = "T_event_members";

    public void insertEventMember(int memberId, int eventId, int sportEventId) {
        String query = "INSERT INTO " + TABLE_NAME + " (member_id, event_id, sportEvent_id) VALUES (?, ?, ?)";
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

    public void updateEventMember(int eventMemberId, int memberId, int eventId, int sportEventId) {
        String query = "UPDATE " + TABLE_NAME + " SET member_id = ?, event_id = ?, sportEvent_id = ? WHERE eventMemberId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, memberId);
            preparedStatement.setInt(2, eventId);
            preparedStatement.setInt(3, sportEventId);
            preparedStatement.setInt(4, eventMemberId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Aktualisieren des EventMembers: " + e.getMessage(), e);
        }
    }

    public void deleteEventMember(int eventMemberId) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE eventMemberId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, eventMemberId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen des EventMembers: " + e.getMessage(), e);
        }
    }

    public EventMember getEventMemberById(int eventMemberId) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE eventMemberId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, eventMemberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new EventMember(
                            resultSet.getInt("eventMemberId"),
                            resultSet.getInt("member_id"),
                            resultSet.getInt("event_id"),
                            resultSet.getInt("sportEvent_id")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen des EventMembers: " + e.getMessage(), e);
        }
        return null;
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
                        resultSet.getInt("member_id"),
                        resultSet.getInt("event_id"),
                        resultSet.getInt("sportEvent_id")
                );
                eventMembers.add(em);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der EventMembers: " + e.getMessage(), e);
        }
        return eventMembers;
    }
}
