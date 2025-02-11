package DatabaseOperations;

import Model.Event;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDatabaseOperations {
    private static final String TABLE_NAME = "T_events";

    public void insertEvent(String administratorId, String name, String status) {
        String query = "INSERT INTO " + TABLE_NAME + " (administratorId, name, status) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, administratorId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, status);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Hinzufügen des Events: " + e.getMessage(), e);
        }
    }

    public void deleteEvent(int eventId) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE eventId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, eventId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen des Events: " + e.getMessage(), e);
        }
    }

    public void updateEvent(int eventId, String administratorId, String name, String status) {
        String query = "UPDATE " + TABLE_NAME + " SET administratorId = ?, name = ?, status = ? WHERE eventId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, administratorId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, status);
            preparedStatement.setInt(4, eventId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Aktualisieren des Events: " + e.getMessage(), e);
        }
    }

    public Event getEventById(int eventId) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE eventId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, eventId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Event(
                            resultSet.getInt("eventId"),
                            resultSet.getString("administratorId"),
                            resultSet.getString("name"),
                            resultSet.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen des Events: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = DBConnection.Verbindung();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                events.add(new Event(
                        resultSet.getInt("eventId"),
                        resultSet.getString("administratorId"),
                        resultSet.getString("name"),
                        resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der Events: " + e.getMessage(), e);
        }
        return events;
    }
}
