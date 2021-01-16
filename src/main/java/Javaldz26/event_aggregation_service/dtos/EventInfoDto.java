package Javaldz26.event_aggregation_service.dtos;

import Javaldz26.event_aggregation_service.entities.User;

import java.time.LocalDateTime;

public class EventInfoDto {

    private Long id;

    private String eventTitle;

    private String eventDescription;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private User owner;

    public EventInfoDto(Long id, String eventTitle, String eventDescription, LocalDateTime startDate, LocalDateTime endDate, User user) {
        this.id = id;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.owner = user;
    }

    public Long getId() {
        return id;
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

    public User getOwner() {
        return owner;
    }
}
