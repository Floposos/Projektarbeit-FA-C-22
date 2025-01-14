package Logic;

import DatabaseOperations.AdministratorDatabaseOperations;
import DatabaseOperations.MemberDatabaseOperations;

import java.time.LocalDate;

public class AdministratorManager {

    private AdministratorDatabaseOperations adminDbOps;

    public AdministratorManager() {
        this.adminDbOps = new AdministratorDatabaseOperations();
    }

    public void addAdmin(String firstName, String lastName, String userId, String password) {
        // Logic to add a new member
        if (userId == null || userId.trim().isEmpty() || firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültiger Name.");
        }
        adminDbOps.insertAdmin(firstName, lastName, userId, password);
    }

    public void removeAdmin(int userId) {
        // Logic to remove a member
    }

    public void updateMember(int memberId, String name, int clubId) {
        // Logic to update member details
    }
}
