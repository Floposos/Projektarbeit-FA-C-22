package DatabaseOperations;
import Model.Member;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import DBConnection.DBConnection;

public class MemberDatabaseOperations {

    private static final String TABLE_NAME = "T_members";
    public void insertMember(String firstName, String lastName, LocalDate birthDate, int clubId) throws SQLException {
        // Database query to insert a member
        String query = "INSERT INTO " + TABLE_NAME + "(first_name, last_name, birth_date, club_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDate(3, Date.valueOf(birthDate));
            preparedStatement.setInt(4, clubId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Hinzufügen des Mitglieds: " + e.getMessage(), e);
        }
    }

    public void deleteMember(int id) {
        // Database query to delete a member
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen des Mitglieds: " + e.getMessage(), e);
        }
    }

    public void updateMember(int id, String name, int clubId) {
        // Database query to update a member's details
        String query = "UPDATE " + TABLE_NAME + " SET name = ?, club_id = ? WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, clubId);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Aktualisieren des Mitglieds: " + e.getMessage(), e);
        }
    }

    public Member getMemberById(int id) {
        // Database query to retrieve a member by ID
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Member(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getInt("club_id"),
                            resultSet.getDate("birth_date").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen des Mitglieds: " + e.getMessage(), e);
        }
        return null;
    }
}
