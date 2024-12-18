package Logic;

import Model.Club;
import DatabaseOperations.ClubDatabaseOperations;

public class ClubManager {
    private ClubDatabaseOperations clubDbOps;

    public ClubManager() {
        this.clubDbOps = new ClubDatabaseOperations();
    }

    public void addClub(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Der Name des Vereins darf nicht leer oder null sein.");
        }
        clubDbOps.insertClub(name);
    }

    public void deleteClub(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Ungültige Vereins-ID.");
        }
        clubDbOps.deleteClub(id);
    }

    public void updateClub(int id, String name) {
        if (id <= 0 || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültige Eingaben für die Aktualisierung des Vereins.");
        }
        clubDbOps.updateClub(id, name);
    }

    public Club getClubById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Ungültige Vereins-ID.");
        }
        return clubDbOps.getClubById(id);
    }
}
