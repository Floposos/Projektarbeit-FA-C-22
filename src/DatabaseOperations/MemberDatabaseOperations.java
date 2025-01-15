package DatabaseOperations;
import Model.Member;

import java.sql.*;
import java.time.LocalDate;

public class MemberDatabaseOperations {

    private static final String TABLE_NAME = "T_members";
    public void insertMember(int clubId, String firstName, String lastName, LocalDate birthDate)  {
        // Database query to insert a member
        String query = "INSERT INTO " + TABLE_NAME + "(first_name, last_name, birth_date, club_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clubId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setDate(4, Date.valueOf(birthDate));

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

    public void updateMember(int memberId, int clubId, String firstName, String lastName, LocalDate birthDate) {
        // Database query to update a member's details
        String query = "UPDATE " + TABLE_NAME + " SET club_id = ?, lastName = ?, firstName = ?, birthDate = ? WHERE memberId = ?";
        try (Connection connection = DBConnection.Verbindung();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, memberId);
            preparedStatement.setInt(2, clubId);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);
            preparedStatement.setDate(5, Date.valueOf(birthDate));

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
                            resultSet.getInt("club_id"),
                            resultSet.getInt("memberId"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
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
