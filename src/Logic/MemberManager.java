package Logic;

import Model.Member;
import DatabaseOperations.MemberDatabaseOperations;
import java.time.LocalDate;

public class MemberManager {

    private MemberDatabaseOperations memberDbOps;

    public MemberManager() {
        this.memberDbOps = new MemberDatabaseOperations();
    }
    public void addMember(String firstName, String lastName, int clubId, LocalDate birthDate) {
        // Logic to add a new member
        if (clubId <= 0 || firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültiger Name.");
        }
        memberDbOps.insertMember(firstName, lastName, clubId, birthDate);
    }

    public void removeMember(int memberId) {
        // Logic to remove a member
    }

    public void updateMember(int memberId, String name, int clubId) {
        // Logic to update member details
    }
}
