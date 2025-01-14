package DatabaseOperations;

import DBConnection.DBConnection;
import Model.Club;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubDatabaseOperations {
    private static final String TABLE_NAME = "T_clubs";

    public void insertClub(String name, String password) {
        String query = "INSERT INTO " + TABLE_NAME + " (name, password) VALUES (?,b?)";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Hinzufügen des Vereins: " + e.getMessage(), e);
        }
    }

    public void deleteClub(int id) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen des Vereins: " + e.getMessage(), e);
        }
    }

    public void updateClub(int id, String name, String password) {
        String query = "UPDATE " + TABLE_NAME + " SET name = ?, password = ? WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Aktualisieren des Vereins: " + e.getMessage(), e);
        }
    }

    public Club getClubById(int id) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Club(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("password"));
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
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der Vereine: " + e.getMessage(), e);
        }
        return clubs;
    }
}
