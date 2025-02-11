package Logic;

import Model.Sport;
import DatabaseOperations.SportDatabaseOperations;
import java.util.List;

public class SportManager {
    private SportDatabaseOperations sportDbOps;

    public SportManager() {
        this.sportDbOps = new SportDatabaseOperations();
    }

    public void addSport(String name, String resultType) {
        if (name == null || name.trim().isEmpty() || resultType == null || resultType.trim().isEmpty()) {
            throw new IllegalArgumentException("Der Name und die Ergebnisart der Sportart dürfen nicht leer oder null sein.");
        }
        sportDbOps.insertSport(name, resultType);
    }

    public void deleteSport(int sportId) {
        if (sportId <= 0) {
            throw new IllegalArgumentException("Ungültige Sport-ID.");
        }
        sportDbOps.deleteSport(sportId);
    }

    public void updateSport(int id, String name, String resultType) {
        if (id <= 0 || name == null || name.trim().isEmpty() || resultType == null || resultType.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültige Eingaben für die Aktualisierung der Sportart.");
        }
        sportDbOps.updateSport(id, name, resultType);
    }

    public Sport getSportById(int sportId) {
        if (sportId <= 0) {
            throw new IllegalArgumentException("Ungültige Sport-ID.");
        }
        return sportDbOps.getSportById(sportId);
    }

    public List<Sport> getAllSports() {
        return sportDbOps.getAllSports();
    }
}
