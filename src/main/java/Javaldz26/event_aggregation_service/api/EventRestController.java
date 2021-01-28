package Javaldz26.event_aggregation_service.api;

import Javaldz26.event_aggregation_service.dtos.EventApiDto;
import Javaldz26.event_aggregation_service.services.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventRestController {

    private final EventService eventService;

    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<EventApiDto> getFutureEvents(
            @RequestParam (name = "filterByDate", defaultValue = "false") boolean filterByDate,
            @RequestParam(name = "beforeDate", required = false) String beforeDate,
            @RequestParam(name = "afterDate", required = false) String afterDate) {

        return eventService.getFutureEvents(filterByDate, afterDate, beforeDate);
    }
}
