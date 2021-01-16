package Javaldz26.event_aggregation_service.controllers;

import Javaldz26.event_aggregation_service.dtos.request.NewEventForm;
import Javaldz26.event_aggregation_service.services.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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

    @PostMapping("/posts/add")
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
}
