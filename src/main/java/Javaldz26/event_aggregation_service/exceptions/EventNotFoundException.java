package Javaldz26.event_aggregation_service.exceptions;

public class EventNotFoundException extends RuntimeException {

    private Long eventId;

    public EventNotFoundException(Long eventId) {
        this.eventId = eventId;
    }

    @Override
    public String getMessage() {
        return String.format("Event not found: %d", eventId);
    }

    public Long getEventId() {
        return eventId;
    }
}
