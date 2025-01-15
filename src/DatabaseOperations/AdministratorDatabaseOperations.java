package DatabaseOperations;

import Model.Administrator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDatabaseOperations {
    private static final String TABLE_NAME = "T_administrators";

    public void insertAdmin(String firstName, String lastName, String password) {
        String query = "INSERT INTO " + TABLE_NAME + " (first_name, last_name, password) VALUES (?, ?, ?)";
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

    public void deleteAdmin(String administratorId) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE administratorId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, administratorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen des Administrators: " + e.getMessage(), e);
        }
    }

    public void updateAdmin(String administratorId, String firstName, String lastName, String password) {
        String query = "UPDATE " + TABLE_NAME + " SET first_name = ?, last_name = ?, password = ? WHERE administratorId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, administratorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Aktualisieren des Administrators: " + e.getMessage(), e);
        }
    }

    public Administrator getAdminById(String administratorId) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE administratorId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, administratorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Administrator(
                            resultSet.getString("administratorId"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
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
                        resultSet.getString("administratorId"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("password")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der Administratoren: " + e.getMessage(), e);
        }
        return administrators;
    }
}
