package Javaldz26.event_aggregation_service.controllers.error;

import Javaldz26.event_aggregation_service.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserDoesntExistException.class)
    public String handle(UserDoesntExistException e) {
        log.warn("Global exception handling for: {}", e.getMessage());

        return "canNotLoginPage";
    }

    @ExceptionHandler(Exception.class)
    public String handle(Exception e) {
        log.error("Unknown exception: {}", e.getMessage());

        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserWithSuchEmailExistsException.class)
    public String handle(UserWithSuchEmailExistsException e) {
        log.warn("Global exception handling for:  {}", e.getMessage());

        return "redirect:/userAlreadyRegisteredPage";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EventNotFoundException.class)
    public String handle(EventNotFoundException e) {
        log.warn("Couldn't find event with id: {}", e.getEventId());
        return "events/noEventFound";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoAuthorizationToPerformTheAction.class)
    public String handle(NoAuthorizationToPerformTheAction e) {
        log.warn("Global exception handling for: {}", e.getMessage());

        return "redirect:/canNotPerformTheActionPage";
    }

}
