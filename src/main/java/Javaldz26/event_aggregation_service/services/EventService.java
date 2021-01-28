package Javaldz26.event_aggregation_service.services;

import Javaldz26.event_aggregation_service.dao.EventCommentRepository;
import Javaldz26.event_aggregation_service.dao.EventRepository;
import Javaldz26.event_aggregation_service.dao.UserRepository;
import Javaldz26.event_aggregation_service.dao.UsersRegisteredForEventsRepository;
import Javaldz26.event_aggregation_service.dtos.EventCommentDto;
import Javaldz26.event_aggregation_service.dtos.EventInfoDto;
import Javaldz26.event_aggregation_service.dtos.EventWithCommentsDto;
import Javaldz26.event_aggregation_service.dtos.request.NewCommentForm;
import Javaldz26.event_aggregation_service.entities.Event;
import Javaldz26.event_aggregation_service.dtos.request.NewEventForm;
import Javaldz26.event_aggregation_service.entities.EventComment;
import Javaldz26.event_aggregation_service.entities.User;
import Javaldz26.event_aggregation_service.entities.UsersRegisteredForEvents;
import Javaldz26.event_aggregation_service.exceptions.EventNotFoundException;
import Javaldz26.event_aggregation_service.exceptions.NoAuthorizationToPerformTheAction;
import Javaldz26.event_aggregation_service.exceptions.UserDoesntExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventCommentRepository eventCommentRepository;
    private final UserRepository userRepository;
    private final UserContextService userContextService;
    private final UsersRegisteredForEventsRepository usersRegisteredForEventsRepository;

    public EventService(EventRepository eventRepository, EventCommentRepository eventCommentRepository, UserRepository userRepository, UserContextService userContextService, UsersRegisteredForEventsRepository usersRegisteredForEventsRepository) {
        this.eventRepository = eventRepository;
        this.eventCommentRepository = eventCommentRepository;
        this.userRepository = userRepository;
        this.userContextService = userContextService;
        this.usersRegisteredForEventsRepository = usersRegisteredForEventsRepository;
    }

    @Transactional
    public void saveNewEvent(NewEventForm newEventForm) {
        final User owner = userRepository.findUserByEmail(userContextService.getCurrentlyLoggedUserEmail())
                .orElseThrow(() -> new NoAuthorizationToPerformTheAction(userContextService.getCurrentlyLoggedUserEmail()));

        final Event event = new Event();

            event.setEventTitle(newEventForm.getEventTitle());
            event.setEventDescription(newEventForm.getEventDescription());
            event.setStartDate(newEventForm.getStartDate());
            event.setEndDate(newEventForm.getEndDate());
            event.setOwner(owner);

    }

    public List<EventInfoDto> getCurrentAndFutureEvents() {
        LocalDateTime currentTime = LocalDateTime.now();
        log.info("GET current Time {}", currentTime);

        return eventRepository.findAllByEndDateAfter(currentTime, Sort.by("startDate").ascending())
                .stream()
                .map(this::buildEventInfoDto)
                .collect(Collectors.toList());

    }

    private EventInfoDto buildEventInfoDto(Event event) {
        return new EventInfoDto(event.getId(),
                event.getEventTitle(),
                event.getEventDescription(),
                event.getStartDate(),
                event.getEndDate());
    }

    public List<EventInfoDto> searchEventByEventTitleFragment(String searchPhrase) {

        return eventRepository.findEventByEventTitleContaining(searchPhrase, Sort.by("startDate"))
                .stream()
                .map(this::buildEventInfoDto)
                .collect(Collectors.toList());
    }

    public Optional<EventInfoDto> getSingleEventInfo(Long eventId) {

       return eventRepository
                .findById(eventId)
                .map(this::buildEventInfoDto);
    }

    public Optional<EventWithCommentsDto> getSingleEventWithCommentsDto(Long eventId) {

        return eventRepository.findById(eventId)
                .map(this::addCommentsToDto)
                .map(eventWithCommentsDto -> {
                    return addCommentsToDto(eventId, eventWithCommentsDto);
                });
    }

    private EventWithCommentsDto addCommentsToDto(Long eventId, EventWithCommentsDto eventWithCommentsDto) {
        final List<EventCommentDto> eventCommentsDto = eventCommentRepository
                .findByEventId(eventId, Sort.by("commentAdded").descending())
                .stream()
                .map(this::getEventCommentDto)
                .collect(Collectors.toList());

        eventWithCommentsDto.setEventComments(eventCommentsDto);
        return eventWithCommentsDto;
    }

    private EventCommentDto getEventCommentDto(EventComment eventComment) {
        return new EventCommentDto(eventComment.getId(),
                eventComment.getCommentText(),
                eventComment.getCommentatorNickname(),
                eventComment.getCommentAdded());
    }


    private EventWithCommentsDto addCommentsToDto(Event event) {
        return new EventWithCommentsDto(event.getId(),
                event.getEventTitle(),
                event.getEventDescription(),
                event.getStartDate(),
                event.getEndDate());
    }

    @Transactional
    public void addNewComment(Long eventId, NewCommentForm newCommentForm, String currentlyLoggedUserEmail) {
        final Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));

        final User user = userRepository.findUserByEmail(currentlyLoggedUserEmail)
                .orElseThrow(() ->new UserDoesntExistException(currentlyLoggedUserEmail));

        final EventComment eventComment = new EventComment();

        eventComment.setCommentText(newCommentForm.getCommentText());
        eventComment.setCommentatorNickname(newCommentForm.getCommentatorNickname());
        eventComment.setCommentator(user);
        eventComment.setEvent(event);

    }

    @Transactional
    public void signUserForEvent(Long eventId, String currentlyLoggedUserEmail) {

        final User user = userRepository.findUserByEmail(currentlyLoggedUserEmail)
                .orElseThrow(() -> new NoAuthorizationToPerformTheAction(currentlyLoggedUserEmail));

        final Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));

        final UsersRegisteredForEvents usersRegisteredForEvents = new UsersRegisteredForEvents();

        usersRegisteredForEvents.setRegisteredUserEmail(user.getEmail());
        usersRegisteredForEvents.setRegisteredUserNickname(user.getNickname());
        usersRegisteredForEvents.addEvent(event);
        usersRegisteredForEvents.addUser(user);

        usersRegisteredForEventsRepository.save(usersRegisteredForEvents);

    }

    @Transactional
    public void signOffUserForEvent(Long eventId, String currentlyLoggedUserEmail) {

        final User user = userRepository.findUserByEmail(currentlyLoggedUserEmail)
                .orElseThrow(() -> new NoAuthorizationToPerformTheAction(currentlyLoggedUserEmail));

        final Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));

        final UsersRegisteredForEvents usersRegisteredForEvents = new UsersRegisteredForEvents();

        usersRegisteredForEvents.setRegisteredUserEmail(user.getEmail());
        usersRegisteredForEvents.setRegisteredUserNickname(user.getNickname());
        usersRegisteredForEvents.addEvent(event);
        usersRegisteredForEvents.addUser(user);

        usersRegisteredForEventsRepository.delete(usersRegisteredForEvents);
    }

    public boolean isSignedForEvent(Long eventId, String currentlyLoggedUserEmail) {
        return usersRegisteredForEventsRepository.existsByIdAndRegisteredUserEmail(eventId, userContextService.getCurrentlyLoggedUserEmail());
    }
}
