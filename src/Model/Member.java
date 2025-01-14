package Model;
import java.time.LocalDate;

public class Member {

    private int memberId;
    private String firstName;
    private String lastName;
    private int clubId;
    LocalDate birthDate;

    public Member(int memberId, String firstName, String lastName, int clubId, LocalDate birthDate) {

        this.birthDate = birthDate;
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.clubId = clubId;

    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return firstName;
    }

    public void setLastName(String firstName) {
        this.lastName = lastName;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}