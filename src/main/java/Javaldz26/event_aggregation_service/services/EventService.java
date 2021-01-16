package Javaldz26.event_aggregation_service.services;

//import Javaldz26.event_aggregation_service.dao.EventCommentRepository;
import Javaldz26.event_aggregation_service.dao.EventRepository;
import Javaldz26.event_aggregation_service.dtos.EventInfoDto;
import Javaldz26.event_aggregation_service.entities.Event;
import Javaldz26.event_aggregation_service.dtos.request.NewEventForm;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
//    private final EventCommentRepository eventCommentRepository;


    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void saveNewEvent(NewEventForm newEventForm) {
        final Event event = new Event();

        event.setEventTitle(newEventForm.getEventTitle());
        event.setEventDescription(newEventForm.getEventDescription());
        event.setStartDate(newEventForm.getStartDate());
        event.setEndDate(newEventForm.getEndDate());

        eventRepository.save(event);
    }

    public List<EventInfoDto> getAllEventsSortedByClosest() {

        return eventRepository.findAll(Sort.by("startDate").ascending())
                .stream()
                .map(event -> new EventInfoDto(event.getId(),
                        event.getEventTitle(),
                        event.getEventDescription(),
                        event.getStartDate(),
                        event.getEndDate(),
                event.getOwner()))
                .collect(Collectors.toList());

    }

    public List<EventInfoDto> searchEventByEventTitleFragment(String searchPhrase) {

        return eventRepository.findEventByEventTitleContainingIgnoreCaseOrderByStartDate(searchPhrase)
                .stream()
                .map(event -> new EventInfoDto(event.getId(),
                        event.getEventTitle(),
                        event.getEventDescription(),
                        event.getStartDate(),
                        event.getEndDate(),
                        event.getOwner()))
                .collect(Collectors.toList());

    }

    public Optional<EventInfoDto> getSingleEventInfo(Long eventId) {

       return eventRepository
                .findById(eventId)
                .map(event -> new EventInfoDto(event.getId(),
                        event.getEventTitle(),
                        event.getEventDescription(),
                        event.getStartDate(),
                        event.getEndDate(),
                        event.getOwner()));

    }
}
