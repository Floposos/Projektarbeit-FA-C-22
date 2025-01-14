package Model;

public class Administrator {

    private String administratorId;
    private String firstName;
    private String lastName;
    private String password;


    public Administrator(String administratorId, String firstName, String lastName, String password) {

        this.administratorId = administratorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;

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

    public String getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(String userId) {
        this.administratorId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
