package Logic;

import Model.Member;
import DatabaseOperations.MemberDatabaseOperations;

public class MemberManager {

    private MemberDatabaseOperations memberDbOps;

    public MemberManager() {
        this.memberDbOps = new MemberDatabaseOperations();
    }
    public void addMember(String name, int clubId) {
        // Logic to add a new member
        if (clubId <= 0 || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültiger Name.");
        }
        memberDbOps.insertMember(firstName, lastName, birthDate, clubId);
    }

    public void removeMember(int memberId) {
        // Logic to remove a member
    }

    public void updateMember(int memberId, String name, int clubId) {
        // Logic to update member details
    }
}
