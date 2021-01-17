package Javaldz26.event_aggregation_service.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class EventWithCommentsDto {

    private Long eventId;
    private String eventTitle;
    private String eventDescription;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<EventCommentDto> eventComments;

    public EventWithCommentsDto(Long eventId, String eventTitle, String eventDescription, LocalDateTime startDate, LocalDateTime endDate) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getEventId() {
        return eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public List<EventCommentDto> getEventComments() {
        return eventComments;
    }

    public void setEventComments(List<EventCommentDto> eventComments) {
        this.eventComments = eventComments;
    }
}
