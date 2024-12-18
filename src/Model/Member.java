package Model;

public class Member {
    private int id;
    private String name;
    private int clubId;

    public Member(int id, String name, int clubId) {
        this.id = id;
        this.name = name;
        this.clubId = clubId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }
}