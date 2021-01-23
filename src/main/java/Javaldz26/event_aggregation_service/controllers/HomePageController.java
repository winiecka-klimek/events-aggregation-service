package Javaldz26.event_aggregation_service.controllers;

import Javaldz26.event_aggregation_service.services.EventService;
import Javaldz26.event_aggregation_service.services.UserContextService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    private final EventService eventService;

    public HomePageController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {

        model.addAttribute("events", eventService.getCurrentAndFutureEvents());

        return "homePage";
    }

}
