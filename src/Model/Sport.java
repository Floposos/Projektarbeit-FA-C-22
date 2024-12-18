package Model;

public class Sport {
    private int sportId;
    private String name;

    public Sport(int sportId, String name) {
        this.sportId = sportId;
        this.name = name;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

