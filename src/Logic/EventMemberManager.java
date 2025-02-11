package Logic;

import DatabaseOperations.EventMemberDatabaseOperations;
import Model.EventMember;
import java.util.List;

public class EventMemberManager {
    private EventMemberDatabaseOperations eventMemberDbOps;

    public EventMemberManager() {
        this.eventMemberDbOps = new EventMemberDatabaseOperations();
    }

    public void addEventMember(int memberId, int eventId, int sportId) {
        if (memberId <= 0 || eventId <= 0 || sportId <= 0) {
            throw new IllegalArgumentException("Ungültige Eingabedaten für EventMember.");
        }
        eventMemberDbOps.insertEventMember(memberId, eventId, sportId);
    }

    public void deleteEventMember(int eventMemberId) {
        if (eventMemberId <= 0) {
            throw new IllegalArgumentException("Ungültige EventMember-ID.");
        }
        eventMemberDbOps.deleteEventMember(eventMemberId);
    }

    public void updateEventMember(int eventMemberId, int memberId, int eventId, int sportId) {
        if (eventMemberId <= 0 || memberId <= 0 || eventId <= 0 || sportId <= 0) {
            throw new IllegalArgumentException("Ungültige Eingabedaten für die Aktualisierung von EventMember.");
        }
        eventMemberDbOps.updateEventMember(eventMemberId, memberId, eventId, sportId);
    }

    public EventMember getEventMemberById(int eventMemberId) {
        if (eventMemberId <= 0) {
            throw new IllegalArgumentException("Ungültige EventMember-ID.");
        }
        return eventMemberDbOps.getEventMemberById(eventMemberId);
    }

    public List<EventMember> getAllEventMembers() {
        return eventMemberDbOps.getAllEventMembers();
    }
}
