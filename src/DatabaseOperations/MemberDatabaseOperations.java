package DatabaseOperations;
import Model.Member;

import java.sql.*;
import java.time.LocalDate;

public class MemberDatabaseOperations {

    private static final String TABLE_NAME = "T_members";
    public void insertMember(String firstName, String lastName,  int clubId, LocalDate birthDate)  {
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

    public void deleteMember(int memberId) {
        // Database query to delete a member
        String query = "DELETE FROM " + TABLE_NAME + " WHERE memberId = ?";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Löschen des Mitglieds: " + e.getMessage(), e);
        }
    }

    public void updateMember(int memberId, String name, int clubId) {
        // Database query to update a member's details
        String query = "UPDATE " + TABLE_NAME + " SET name = ?, club_id = ? WHERE memberId = ?";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, clubId);
            preparedStatement.setInt(3, memberId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Aktualisieren des Mitglieds: " + e.getMessage(), e);
        }
    }

    public Member getMemberById(int memberId) {
        // Database query to retrieve a member by ID
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE memberId = ?";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Member(
                            resultSet.getInt("memberId"),
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
