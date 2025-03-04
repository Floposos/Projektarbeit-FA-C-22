package DatabaseOperations;

import Model.Administrator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDatabaseOperations {
    private static final String TABLE_NAME = "T_administrator";

    public void insertAdmin(String firstName, String lastName, String password) {
        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Vorname, Nachname und Passwort dürfen nicht leer oder null sein.");
        }

        String query = "INSERT INTO " + TABLE_NAME + " (firstName, lastName, password) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Hinzufügen des Administrators: " + e.getMessage(), e);
        }
    }

    public boolean adminExists(String firstName, String lastName) {
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE firstName = ? AND lastName = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Überprüfen der Administrator-Existenz: " + e.getMessage(), e);
        }
        return false;
    }

    public boolean validateCredentials(int administratorId, String password) {
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE administratorId = ? AND password = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, administratorId);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler bei der Administrator-Authentifizierung: " + e.getMessage(), e);
        }
        return false;
    }

    public void deleteAdmin(int administratorId) {
        if (administratorId <= 0) {
            throw new IllegalArgumentException("Administrator-ID darf nicht leer oder null sein.");
        }
        String query = "DELETE FROM " + TABLE_NAME + " WHERE administratorId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, administratorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen des Administrators: " + e.getMessage(), e);
        }
    }

    public void updateAdmin(int administratorId, String firstName, String lastName, String password) {
        if (administratorId <= 0 ||
                firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Alle Eingaben müssen gültig sein (nicht leer oder null).");
        }
        String query = "UPDATE " + TABLE_NAME + " SET firstName = ?, lastName = ?, password = ? WHERE administratorId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, administratorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Aktualisieren des Administrators: " + e.getMessage(), e);
        }
    }

    public Administrator getAdminById(int administratorId) {
        if (administratorId <= 0) {
            throw new IllegalArgumentException("Administrator-ID darf nicht leer oder null sein.");
        }
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE administratorId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, administratorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Administrator(
                            resultSet.getInt("administratorId"),
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen des Administrators: " + e.getMessage(), e);
        }
        return null;
    }

    public List<Administrator> getAllAdministrators() {
        List<Administrator> administrators = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = DBConnection.Verbindung();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                administrators.add(new Administrator(
                        resultSet.getInt("administratorId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("password")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der Administratoren: " + e.getMessage(), e);
        }
        return administrators;
    }
}
