package Javaldz26.event_aggregation_service.services;

import Javaldz26.event_aggregation_service.dao.EventCommentRepository;
import Javaldz26.event_aggregation_service.dao.EventRepository;
import Javaldz26.event_aggregation_service.dtos.EventInfoDto;
import Javaldz26.event_aggregation_service.entities.Event;
import Javaldz26.event_aggregation_service.dtos.request.NewEventForm;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventCommentRepository eventCommentRepository;


    public EventService(EventRepository eventRepository, EventCommentRepository eventCommentRepository) {
        this.eventRepository = eventRepository;
        this.eventCommentRepository = eventCommentRepository;
    }

    public void saveNewEvent(NewEventForm newEventForm) {
        final Event event = new Event();

        event.setEventTitle(newEventForm.getEventTitle());
        event.setEventDescription(newEventForm.getEventDescription());
        event.setStartDate(newEventForm.getStartDate());
        event.setEndDate(newEventForm.getEndDate());

        eventRepository.save(event);
    }

    public List<EventInfoDto> getAllEventsSortedByNearest() {

        return eventRepository.findAll(Sort.by("startDate").ascending())
                .stream()
                .map(event -> new EventInfoDto(event.getId(),
                        event.getEventTitle(),
                        event.getEventDescription(),
                        event.getStartDate(),
                        event.getEndDate(),
                event.getUser()))
                .collect(Collectors.toList());

    }

}
