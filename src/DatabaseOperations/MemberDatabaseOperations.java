package DatabaseOperations;

import Model.Member;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberDatabaseOperations {

    private static final String TABLE_NAME = "T_members";

    public void insertMember(int clubId, String firstName, String lastName, LocalDate birthDate) {
        String query = "INSERT INTO " + TABLE_NAME + " (first_name, last_name, birth_date, club_id) VALUES (?, ?, ?, ?)";
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
        String query = "UPDATE " + TABLE_NAME + " SET club_id = ?, first_name = ?, last_name = ?, birth_date = ? WHERE memberId = ?";
        try (Connection connection = DBConnection.Verbindung();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clubId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setDate(4, Date.valueOf(birthDate));
            preparedStatement.setInt(5, memberId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Aktualisieren des Mitglieds: " + e.getMessage(), e);
        }
    }

    public Member getMemberById(int memberId) {
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

    // GetAll-Methode: Gibt alle Mitglieder zurück
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        try (Connection connection = DBConnection.Verbindung();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Member m = new Member(
                        resultSet.getInt("club_id"),
                        resultSet.getInt("memberId"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birth_date").toLocalDate()
                );
                members.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der Mitglieder: " + e.getMessage(), e);
        }
        return members;
    }
}
