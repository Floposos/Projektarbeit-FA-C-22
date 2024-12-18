package DatabaseOperations;
import Model.Member;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;

public class MemberDatabaseOperations {
    public void insertMember(String firstName, String lastName, LocalDate birthDate, int clubId) {
        // Database query to insert a member
    }

    public void deleteMember(int id) {
        // Database query to delete a member
    }

    public void updateMember(int id, String name, int clubId) {
        // Database query to update a member's details
    }

    public Member getMemberById(int id) {
        // Database query to retrieve a member by ID
        return null;
    }
}
