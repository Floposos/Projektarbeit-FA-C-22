package DatabaseOperations;

import Model.EventMember;
import java.sql.*;

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
        String query = "UPDATE " + TABLE_NAME + " SET member_id = ?, event_id = ?, sportEvent_id = ? WHERE id = ?";
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

    public void deleteEventMember(int id) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen des EventMembers: " + e.getMessage(), e);
        }
    }

    public EventMember getEventMemberById(int id) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new EventMember(
                            resultSet.getInt("id"),
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
}
