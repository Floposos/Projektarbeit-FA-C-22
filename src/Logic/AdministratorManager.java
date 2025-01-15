package Logic;

import DatabaseOperations.AdministratorDatabaseOperations;
import Model.Administrator;

public class AdministratorManager {

    private AdministratorDatabaseOperations adminDbOps;

    public AdministratorManager() {
        this.adminDbOps = new AdministratorDatabaseOperations();
    }

    public void addAdmin(String firstName, String lastName, String password) {
        if (firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültiger Name oder Passwort.");
        }
        adminDbOps.insertAdmin(firstName, lastName, password);
    }

    public void removeAdmin(String administratorId) {
        if (administratorId == null || administratorId.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültige Administrator-ID.");
        }
        adminDbOps.deleteAdmin(administratorId);
    }

    public void updateAdmin(String administratorId, String firstName, String lastName, String password) {
        if (administratorId == null || administratorId.trim().isEmpty() || firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültige Eingaben für die Aktualisierung des Administrators.");
        }
        adminDbOps.updateAdmin(administratorId, firstName, lastName, password);
    }

    public Administrator getAdminById(String administratorId) {
        if (administratorId == null || administratorId.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültige Administrator-ID.");
        }
        return adminDbOps.getAdminById(administratorId);
    }

    public void listAllAdministrators() {
        adminDbOps.getAllAdministrators().forEach(System.out::println);
    }
}
