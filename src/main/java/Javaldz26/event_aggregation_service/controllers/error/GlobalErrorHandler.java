package Javaldz26.event_aggregation_service.controllers.error;

import Javaldz26.event_aggregation_service.exceptions.EventNotFoundException;
import Javaldz26.event_aggregation_service.exceptions.InvalidCredentialsException;
import Javaldz26.event_aggregation_service.exceptions.UserDoesntExistException;
import Javaldz26.event_aggregation_service.exceptions.UserWithSuchEmailExistsException;
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InvalidCredentialsException.class)
    public String handle(InvalidCredentialsException e) {
        log.warn("Global exception handling for: {}", e.getMessage());

        return "canNotLoginPage";
    }

    @ExceptionHandler(Exception.class)
    public String handle(Exception e) {
        log.error("Unknown exception: {}", e.getMessage());

        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(UserWithSuchEmailExistsException.class)
    public String handle(UserWithSuchEmailExistsException e) {
        log.warn("Global exception handling for:  {}", e.getMessage());

        return "userAlreadyRegisteredPage";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EventNotFoundException.class)
    public String handle(EventNotFoundException e) {
        log.warn("Couldn't find event with id: {}", e.getEventId());
        return "events/noEventFound";
    }

}
