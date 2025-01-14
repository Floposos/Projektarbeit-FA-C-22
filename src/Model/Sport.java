package Model;

public class Sport {
    private int sportId;
    private String name;
    private String resultType;

    public Sport(int sportId, String name, String resultType) {
        this.sportId = sportId;
        this.name = name;
        this.resultType = resultType;
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

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}

