package Javaldz26.event_aggregation_service.services;

//import Javaldz26.event_aggregation_service.dao.EventCommentRepository;
import Javaldz26.event_aggregation_service.dao.EventCommentRepository;
import Javaldz26.event_aggregation_service.dao.EventRepository;
import Javaldz26.event_aggregation_service.dtos.EventCommentDto;
import Javaldz26.event_aggregation_service.dtos.EventInfoDto;
import Javaldz26.event_aggregation_service.dtos.EventWithCommentsDto;
import Javaldz26.event_aggregation_service.dtos.request.NewCommentForm;
import Javaldz26.event_aggregation_service.entities.Event;
import Javaldz26.event_aggregation_service.dtos.request.NewEventForm;
import Javaldz26.event_aggregation_service.entities.EventComment;
import Javaldz26.event_aggregation_service.exceptions.EventNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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

    public List<EventInfoDto> getCurrentAndFutureEvents() {
        LocalDateTime currentTime = LocalDateTime.now();
        log.info("GET current Time {}", currentTime);

        return eventRepository.findAllByToDateAfter(currentTime, Sort.by("startDate").ascending())
                .stream()
                .map(event -> new EventInfoDto(event.getId(),
                        event.getEventTitle(),
                        event.getEventDescription(),
                        event.getStartDate(),
                        event.getEndDate()))
                .collect(Collectors.toList());

    }

    public List<EventInfoDto> searchEventByEventTitleFragment(String searchPhrase) {

        return eventRepository.findEventByEventTitleContainingIgnoreCaseOrderByStartDate(searchPhrase)
                .stream()
                .map(event -> new EventInfoDto(event.getId(),
                        event.getEventTitle(),
                        event.getEventDescription(),
                        event.getStartDate(),
                        event.getEndDate()))
                .collect(Collectors.toList());

    }

    public Optional<EventInfoDto> getSingleEventInfo(Long eventId) {

       return eventRepository
                .findById(eventId)
                .map(event -> new EventInfoDto(event.getId(),
                        event.getEventTitle(),
                        event.getEventDescription(),
                        event.getStartDate(),
                        event.getEndDate()));

    }

    public Optional<EventWithCommentsDto> getSingleEventWithCommentsDto(Long eventId) {

        final Optional<EventWithCommentsDto>  eventWithCommentsDtoOptional = eventRepository.findById(eventId)
                .map(event -> new EventWithCommentsDto(event.getId(),
                        event.getEventTitle(),
                        event.getEventDescription(),
                        event.getStartDate(),
                        event.getEndDate()));

        eventWithCommentsDtoOptional.ifPresent(eventWithCommentsDto -> {
            final List<EventCommentDto> eventCommentsDto = eventCommentRepository
                .findByEventId(eventId, Sort.by("commentAdded").descending())
                    .stream()
                    .map(eventComment -> new EventCommentDto(
                            eventComment.getCommentText(),
                            eventComment.getCommentatorNickname(),
                                    eventComment.getCommentAdded()))
                    .collect(Collectors.toList());

            eventWithCommentsDto.setEventComments(eventCommentsDto);
        });

        return eventWithCommentsDtoOptional;

    }

    public void addNewComment(Long eventId, NewCommentForm newCommentForm) {
        final Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));

        final EventComment eventComment = new EventComment();

        eventComment.setCommentText(newCommentForm.getCommentText());
        eventComment.setCommentatorNickname(newCommentForm.getCommentatorNickname());
        eventComment.setCommentator(newCommentForm.getCommentator());
        eventComment.setEvent(event);

        eventCommentRepository.save(eventComment);

    }

    public List<EventCommentDto> getCommentsForEvent(Long eventId) {
        return null;
    }
}
