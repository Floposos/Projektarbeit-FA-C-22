package DatabaseOperations;

import Model.Sport;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SportDatabaseOperations {
    private static final String TABLE_NAME = "T_sports";

    public void insertSport(String name, String resultType) {
        if (name == null || name.trim().isEmpty() || resultType == null || resultType.trim().isEmpty()) {
            throw new IllegalArgumentException("Name und ResultType dürfen nicht leer oder null sein.");
        }
        String query = "INSERT INTO " + TABLE_NAME + " (name, result_type) VALUES (?, ?)";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, resultType);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Hinzufügen der Sportart: " + e.getMessage(), e);
        }
    }

    public void deleteSport(int sportId) {
        if (sportId <= 0) {
            throw new IllegalArgumentException("Ungültige Sport-ID.");
        }
        String query = "DELETE FROM " + TABLE_NAME + " WHERE sportId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, sportId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen der Sportart: " + e.getMessage(), e);
        }
    }

    public void updateSport(int sportId, String name, String resultType) {
        if (sportId <= 0 || name == null || name.trim().isEmpty() || resultType == null || resultType.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültige Eingaben für die Aktualisierung der Sportart.");
        }
        String query = "UPDATE " + TABLE_NAME + " SET name = ?, result_type = ? WHERE sportId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, resultType);
            preparedStatement.setInt(3, sportId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Aktualisieren der Sportart: " + e.getMessage(), e);
        }
    }

    public Sport getSportById(int sportId) {
        if (sportId <= 0) {
            throw new IllegalArgumentException("Ungültige Sport-ID.");
        }
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE sportId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, sportId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Sport(
                            resultSet.getInt("sportId"),
                            resultSet.getString("name"),
                            resultSet.getString("result_type")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der Sportart: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Sport> getAllSports() {
        List<Sport> sports = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = DBConnection.Verbindung();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                sports.add(new Sport(
                        resultSet.getInt("sportId"),
                        resultSet.getString("name"),
                        resultSet.getString("result_type")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der Sportarten: " + e.getMessage(), e);
        }
        return sports;
    }
}
