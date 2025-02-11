package Logic;

import DatabaseOperations.AdministratorDatabaseOperations;
import Model.Administrator;
import java.util.List;


//Event Manager

public class AdministratorManager {

    private AdministratorDatabaseOperations adminDbOps;

    public AdministratorManager() {
        this.adminDbOps = new AdministratorDatabaseOperations();
    }

    public void addAdmin(String firstName, String lastName, String password) {
        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültiger Name oder Passwort.");
        }
        adminDbOps.insertAdmin(firstName, lastName, password);
    }

    public void deleteAdmin(int administratorId) {
        if (administratorId <= 0) {
            throw new IllegalArgumentException("Ungültige Administrator-ID.");
        }
        adminDbOps.deleteAdmin(administratorId);
    }

    public void updateAdmin(int administratorId, String firstName, String lastName, String password) {
        if (administratorId <= 0 ||
                firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültige Eingaben für die Aktualisierung des Administrators.");
        }
        adminDbOps.updateAdmin(administratorId, firstName, lastName, password);
    }

    public Administrator getAdminById(int administratorId) {
        if (administratorId <= 0) {
            throw new IllegalArgumentException("Ungültige Administrator-ID.");
        }
        return adminDbOps.getAdminById(administratorId);
    }

    public List<Administrator> getAllAdministrators() {
        return adminDbOps.getAllAdministrators();
    }
}
