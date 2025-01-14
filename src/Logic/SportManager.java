package Logic;
import Model.Sport;
import DatabaseOperations.SportDatabaseOperations;

public class SportManager {
    private SportDatabaseOperations sportDbOps;

    public SportManager() {
        this.sportDbOps = new SportDatabaseOperations();
    }

    public void addSport(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Der Name der Sportart darf nicht leer oder null sein.");
        }
        sportDbOps.insertSport(name);
    }

    public void deleteSport(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Ungültige Sport-ID.");
        }
        sportDbOps.deleteSport(id);
    }

    public void updateSport(int id, String name) {
        if (id <= 0 || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültige Eingaben für die Aktualisierung der Sportart.");
        }
        sportDbOps.updateSport(id, name);
    }

    public Sport getSportById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Ungültige Sport-ID.");
        }
        return sportDbOps.getSportById(id);
    }
}
