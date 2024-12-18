package Model;
//******************************************************
// Vielleicht nicht nötig, muss noch geklärt werden!   *
//******************************************************

public class ClubMemberRelation {
    private int clubId;
    private int memberId;

    public ClubMemberRelation(int clubId, int memberId) {
        this.clubId = clubId;
        this.memberId = memberId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
