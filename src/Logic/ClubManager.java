package Logic;

import Model.Club;
import DatabaseOperations.ClubDatabaseOperations;

public class ClubManager {
    private ClubDatabaseOperations clubDbOps;

    public ClubManager() {
        this.clubDbOps = new ClubDatabaseOperations();
    }

    public void addClub(String name, String password) {
        if (name == null || name.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Der Name oder Passwort des Vereins darf nicht leer oder null sein.");
        }
        clubDbOps.insertClub(name, password);
    }

    public void deleteClub(int clubId) {
        if (clubId <= 0) {
            throw new IllegalArgumentException("Ungültige Vereins-ID.");
        }
        clubDbOps.deleteClub(clubId);
    }

    public void updateClub(int clubId, String name, String password) {
        if (clubId <= 0 || name == null || name.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültige Eingaben für die Aktualisierung des Vereins.");
        }
        clubDbOps.updateClub(clubId, name, password);
    }

    public Club getClubById(int clubId) {
        if (clubId <= 0) {
            throw new IllegalArgumentException("Ungültige Vereins-ID.");
        }
        return clubDbOps.getClubById(clubId);
    }
}
