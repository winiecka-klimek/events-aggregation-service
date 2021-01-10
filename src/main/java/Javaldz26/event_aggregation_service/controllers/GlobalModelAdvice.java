package Javaldz26.event_aggregation_service.controllers;


import Javaldz26.event_aggregation_service.services.UserContextService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAdvice {

    private final UserContextService userContextService;

    public GlobalModelAdvice(UserContextService userContextService) {
        this.userContextService = userContextService;
    }

    @ModelAttribute("loggedAs")
    public String getLoggedAs() {
        return userContextService.getCurrentlyLoggedUserEmail();
    }
}
