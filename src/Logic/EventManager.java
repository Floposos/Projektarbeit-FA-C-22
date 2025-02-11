package Logic;

import Model.Event;
import DatabaseOperations.EventDatabaseOperations;
import java.util.List;

public class EventManager {
    private EventDatabaseOperations eventDbOps;

    public EventManager() {
        this.eventDbOps = new EventDatabaseOperations();
    }

    public void addEvent(String administratorId, String name) {
        if (administratorId == null || administratorId.trim().isEmpty() ||
                name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht leer sein.");
        }
        eventDbOps.insertEvent(administratorId, name, "angelegt");
    }

    public void updateEvent(int eventId, String administratorId, String name, String status) {
        if (eventId <= 0 ||
                administratorId == null || administratorId.trim().isEmpty() ||
                name == null || name.trim().isEmpty() ||
                status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Ungültige Eingaben für die Aktualisierung des Events.");
        }
        eventDbOps.updateEvent(eventId, administratorId, name, status);
    }

    public void deleteEvent(int eventId) {
        if (eventId <= 0) {
            throw new IllegalArgumentException("Ungültige Event-ID.");
        }
        eventDbOps.deleteEvent(eventId);
    }

    public Event getEventById(int eventId) {
        if (eventId <= 0) {
            throw new IllegalArgumentException("Ungültige Event-ID.");
        }
        return eventDbOps.getEventById(eventId);
    }

    public List<Event> getAllEvents() {
        return eventDbOps.getAllEvents();
    }
}
