package Model;
import java.time.LocalDate;

public class Member {

    private int memberId;
    private String firstName;
    private String lastName;
    private int clubId;
    LocalDate birthDate;

    public Member(int memberId, int clubId, String firstName, String lastName, LocalDate birthDate) {

        this.memberId = memberId;
        this.clubId = clubId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;

    }

    @Override
    public String toString() {
        return memberId + " - " + firstName + " - " + lastName;
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
        return lastName;
    }

    public void setLastName(String lastName) {
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