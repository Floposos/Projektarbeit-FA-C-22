package DatabaseOperations;

import DBConnection.DBConnection;
import Model.Event;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDatabaseOperations {
    private static final String TABLE_NAME = "T_events";

    public void insertEvent(String name,  String status) {
        String query = "INSERT INTO " + TABLE_NAME + " (name, status) VALUES (?, ?)";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, status);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Hinzufügen des Events: " + e.getMessage(), e);
        }
    }

    public void deleteEvent(int id) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen des Events: " + e.getMessage(), e);
        }
    }

    public void updateEvent(int id, String name, String status) {
        String query = "UPDATE " + TABLE_NAME + " SET name = ?, status = ? WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, status);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Aktualisieren des Events: " + e.getMessage(), e);
        }
    }

    public Event getEventById(int id) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Event(
                            resultSet.getInt("eventId"),
                            resultSet.getInt("administratorId")  ,
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
                        resultSet.getInt("administratorId")  ,
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
