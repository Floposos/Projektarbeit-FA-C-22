package DatabaseOperations;
import DBConnection.DBConnection;
import Model.Sport;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SportDatabaseOperations {
    private static final String TABLE_NAME = "sports";

    public void insertSport(String name, String resultType) {
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

    public void deleteSport(int id) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen der Sportart: " + e.getMessage(), e);
        }
    }

    public void updateSport(int id, String name, String resultType) {
        String query = "UPDATE " + TABLE_NAME + " SET name = ?, result_type = ? WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, resultType);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Aktualisieren der Sportart: " + e.getMessage(), e);
        }
    }

    public Sport getSportById(int id) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Sport(
                            resultSet.getInt("id"),
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
                        resultSet.getInt("id"),
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
