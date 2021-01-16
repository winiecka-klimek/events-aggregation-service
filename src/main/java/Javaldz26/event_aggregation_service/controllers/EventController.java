package Javaldz26.event_aggregation_service.controllers;

import Javaldz26.event_aggregation_service.dtos.EventInfoDto;
import Javaldz26.event_aggregation_service.dtos.request.NewEventForm;
import Javaldz26.event_aggregation_service.services.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("events/add")
    public String showNewEventForm() {
//        final NewEventForm newEventForm= new NewEventForm();
        return "events/newEventForm";
    }

    @PostMapping("/events/add")
    public String handleNewEventForm(@ModelAttribute @Valid NewEventForm newEventForm,
                                     BindingResult bindingResult) {
        log.info("New EVENT: {}", newEventForm);
        log.info("New EVENT ERRORS: {}", bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return "events/newEventForm";
        }

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

        final Optional<EventInfoDto> eventInfoDtoOptional = eventService.getSingleEventInfo(eventId);
//        final Optional<PostInfoDto> postInfoDtoOptional = postService.getSinglePostInfoWithComments(postId);

        if (eventInfoDtoOptional.isEmpty()) {
            return "events/noEventFound";
        }

        model.addAttribute("event", eventInfoDtoOptional.get());

//        List<CommentDto> postComments = postService.getCommentsForPost(postId);
//        model.addAttribute("postComments", postComments);

        return "events/singleEventPage";
    }

}
