package Logic;

import DatabaseOperations.EventDatabaseOperations;
import DatabaseOperations.SportEventDatabaseOperations;
import DatabaseOperations.EventMemberDatabaseOperations;
import Model.EventMember;

import java.util.List;

public class EventMemberManager {
    private final EventMemberDatabaseOperations eventMemberDb;
    private final EventDatabaseOperations eventDb;
    private final SportEventDatabaseOperations sportEventDb;

    public EventMemberManager() {
        this.eventMemberDb = new EventMemberDatabaseOperations();
        this.eventDb = new EventDatabaseOperations();
        this.sportEventDb = new SportEventDatabaseOperations();
    }

    public void registerMemberForEvent(int memberId, int eventId, int sportEventId) {
        if (!eventDb.eventExists(eventId)) {
            throw new IllegalArgumentException("Event mit ID " + eventId + " existiert nicht.");
        }
        if (!sportEventDb.sportEventExists(sportEventId)) {
            throw new IllegalArgumentException("SportEvent mit ID " + sportEventId + " existiert nicht.");
        }
        if (eventMemberDb.isMemberAlreadyRegistered(memberId, eventId)) {
            throw new IllegalArgumentException("Mitglied ist bereits für dieses Event angemeldet.");
        }

        eventMemberDb.insertEventMember(memberId, eventId, sportEventId);
    }

    public void unregisterMemberFromEvent(int memberId, int eventId) {
        int eventMemberId = eventMemberDb.getEventMemberId(memberId, eventId);
        if (eventMemberId == -1) {
            throw new IllegalArgumentException("Mitglied ist nicht für dieses Event angemeldet.");
        }

        eventMemberDb.deleteEventMember(memberId, eventId);
    }

    public List<EventMember> getAllEventMembers() {
        return eventMemberDb.getAllEventMembers();
    }
}
