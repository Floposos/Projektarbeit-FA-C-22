package Logic;

import Model.Event;
import DatabaseOperations.EventDatabaseOperations;

public class EventManager {
    private EventDatabaseOperations eventDbOps;

    public EventManager() {
        this.eventDbOps = new EventDatabaseOperations();
    }

    public void createEvent(String administratorId, String name,  String status) {
        if (administratorId == null || administratorId.trim().isEmpty() || name == null || name.trim().isEmpty() || status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Der Name des Events darf nicht leer oder null sein.");
        }
        eventDbOps.insertEvent(administratorId ,name, status);
    }

    public void updateEvent(int id, String administratorId, String name, String status) {
        if (id <= 0 || name == null || name.trim().isEmpty() || administratorId == null || administratorId.trim().isEmpty() || status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültige Eingaben für die Aktualisierung des Events.");
        }
        eventDbOps.updateEvent(id, administratorId, name, status);
    }

    public void deleteEvent(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Ungültige Event-ID.");
        }
        eventDbOps.deleteEvent(id);
    }

    public Event getEventById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Ungültige Event-ID.");
        }
        return eventDbOps.getEventById(id);
    }
}
