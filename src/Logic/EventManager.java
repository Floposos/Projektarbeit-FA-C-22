package Logic;

import Model.Event;
import DatabaseOperations.EventDatabaseOperations;

public class EventManager {
    private EventDatabaseOperations eventDbOps;

    public EventManager() {
        this.eventDbOps = new EventDatabaseOperations();
    }

    public void createEvent(String name, String date, String status) {
        if (name == null || name.trim().isEmpty() || date == null || date.trim().isEmpty()|| status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Der Name und das Datum des Events dürfen nicht leer oder null sein.");
        }
        eventDbOps.insertEvent(name, date, status);
    }

    public void updateEvent(int id, String name, String date, String status) {
        if (id <= 0 || name == null || name.trim().isEmpty() || date == null || date.trim().isEmpty() || status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültige Eingaben für die Aktualisierung des Events.");
        }
        eventDbOps.updateEvent(id, name, date, status);
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
