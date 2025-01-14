package Model;

public class Club {
    private int clubId;
    private String name;
    private String password;

    public Club(int clubId, String name, String password) {
        this.clubId = clubId;
        this.name = name;
        this.password = password;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
