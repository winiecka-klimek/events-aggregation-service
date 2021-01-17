package Javaldz26.event_aggregation_service.controllers;

import Javaldz26.event_aggregation_service.dtos.EventCommentDto;
import Javaldz26.event_aggregation_service.dtos.EventInfoDto;
import Javaldz26.event_aggregation_service.dtos.EventWithCommentsDto;
import Javaldz26.event_aggregation_service.dtos.request.NewAttendeeForm;
import Javaldz26.event_aggregation_service.dtos.request.NewCommentForm;
import Javaldz26.event_aggregation_service.dtos.request.NewEventForm;
import Javaldz26.event_aggregation_service.services.EventService;
import Javaldz26.event_aggregation_service.services.UserContextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class EventController {

    private final EventService eventService;
    private final UserContextService userContextService;

    public EventController(EventService eventService, UserContextService userContextService) {
        this.eventService = eventService;
        this.userContextService = userContextService;
    }

    @GetMapping("events/add")
    public String showNewEventForm() {
//        final NewEventForm newEventForm= new NewEventForm();
        return "events/newEventForm";
    }

    @PostMapping("/events/add")
    public String handleNewEventForm(@ModelAttribute @Valid NewEventForm newEventForm,
                                     BindingResult bindingResult, Model  model) {
        log.info("New EVENT: {}", newEventForm);
        log.info("New EVENT ERRORS: {}", bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return "events/newEventForm";
        }

        model.addAttribute("loggedAs", userContextService.getCurrentlyLoggedUserEmail());

        eventService.saveNewEvent(newEventForm);

        return "redirect:/";
    }

    @GetMapping("/events/search")
    public String findEventByTitle(Model model, String searchPhrase){

        model.addAttribute("searchFraze", searchPhrase);
        model.addAttribute("events", eventService.searchEventByEventTitleFragment(searchPhrase));

        return "redirect:/events/searchResultsPage";

    }

    @GetMapping("/events/{eventId}")
    public String showSinglePostPage(@PathVariable Long eventId, Model model) {

        final Optional<EventWithCommentsDto> eventInfoDtoOptional = eventService.getSingleEventWithCommentsDto(eventId);


        if (eventInfoDtoOptional.isEmpty()) {
            return "events/noEventFound";
        }

        model.addAttribute("event", eventInfoDtoOptional.get());

        List<EventCommentDto> eventComments = eventService.getCommentsForEvent(eventId);
        model.addAttribute("eventComments", eventComments);

        return "events/singleEventPage";
    }

    @PostMapping("/events/{eventId}/comment/add")
    public String handleNewCommentForm(@PathVariable Long eventId,
                                       @ModelAttribute @Valid NewCommentForm newCommentForm,
                                       BindingResult bindingResult, Model model) {

        log.info("New COMMENT: {}", newCommentForm);
        log.info("New COMMENT ERRORS: {}", bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return "redirect:/events/" + eventId;
        }

        model.addAttribute("loggedAs", userContextService.getCurrentlyLoggedUserEmail());

        eventService.addNewComment(eventId, newCommentForm);

        return "redirect:/events/" + eventId;
    }

    @PostMapping("/events/{eventId}/sign-up-for-event")
    public String submitForEventForm(@PathVariable Long eventId,
                                     @ModelAttribute @Valid NewAttendeeForm newAttendeeForm,
                                     BindingResult bindingResult, Model model) {

    }

}
