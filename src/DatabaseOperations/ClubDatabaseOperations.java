package DatabaseOperations;

import Model.Club;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubDatabaseOperations {
    private static final String TABLE_NAME = "T_clubs";

    public void insertClub(String name, String password) {
        if (name == null || name.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Name und Passwort dürfen nicht leer oder null sein.");
        }
        String query = "INSERT INTO " + TABLE_NAME + " (name, password) VALUES (?, ?)";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Hinzufügen des Vereins: " + e.getMessage(), e);
        }
    }

    public void deleteClub(int clubId) {
        if (clubId <= 0) {
            throw new IllegalArgumentException("Ungültige Club-ID.");
        }
        String query = "DELETE FROM " + TABLE_NAME + " WHERE clubId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, clubId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen des Vereins: " + e.getMessage(), e);
        }
    }

    public void updateClub(int clubId, String name, String password) {
        if (clubId <= 0 || name == null || name.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültige Eingaben für die Aktualisierung des Vereins.");
        }
        String query = "UPDATE " + TABLE_NAME + " SET name = ?, password = ? WHERE clubId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, clubId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Aktualisieren des Vereins: " + e.getMessage(), e);
        }
    }

    public Club getClubById(int clubId) {
        if (clubId <= 0) {
            throw new IllegalArgumentException("Ungültige Club-ID.");
        }
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE clubId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, clubId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Club(
                            resultSet.getInt("clubId"),
                            resultSet.getString("name"),
                            resultSet.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen des Vereins: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Club> getAllClubs() {
        List<Club> clubs = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = DBConnection.Verbindung();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                clubs.add(new Club(
                        resultSet.getInt("clubId"),
                        resultSet.getString("name"),
                        resultSet.getString("password")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der Vereine: " + e.getMessage(), e);
        }
        return clubs;
    }

    public boolean validateCredentials(String name, String password) {
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE name = ? AND password = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler bei der Verein-Authentifizierung: " + e.getMessage(), e);
        }
        return false;
    }

    public boolean clubExists(String name) {
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE name = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Überprüfen der Vereins-Existenz: " + e.getMessage(), e);
        }
        return false;
    }
}
