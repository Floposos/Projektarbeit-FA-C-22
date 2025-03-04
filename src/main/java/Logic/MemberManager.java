package Logic;

import Model.Member;
import DatabaseOperations.MemberDatabaseOperations;
import java.time.LocalDate;
import java.util.List;

public class MemberManager {

    private MemberDatabaseOperations memberDbOps;

    public MemberManager() {
        this.memberDbOps = new MemberDatabaseOperations();
    }

    public void addMember(int clubId, String firstName, String lastName, LocalDate birthDate) {
        if (clubId <= 0 || firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty() || birthDate == null) {
            throw new IllegalArgumentException("Ungültige Eingaben für das Mitglied.");
        }
        memberDbOps.insertMember(clubId, firstName, lastName, birthDate);
    }

    public void deleteMember(int memberId) {
        if (memberId <= 0) {
            throw new IllegalArgumentException("Ungültige Mitglieds-ID.");
        }
        memberDbOps.deleteMember(memberId);
    }

    public void updateMember(int memberId, int clubId, String firstName, String lastName, LocalDate birthDate) {
        if (memberId <= 0 || clubId <= 0 || firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty() || birthDate == null) {
            throw new IllegalArgumentException("Ungültige Eingaben für die Aktualisierung des Mitglieds.");
        }
        memberDbOps.updateMember(memberId, clubId, firstName, lastName, birthDate);
    }

    public Member getMemberById(int memberId) {
        if (memberId <= 0) {
            throw new IllegalArgumentException("Ungültige Mitglieds-ID.");
        }
        return memberDbOps.getMemberById(memberId);
    }

    public List<Member> getAllMembers() {
        return memberDbOps.getAllMembers();
    }
}
