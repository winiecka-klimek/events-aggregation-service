package Javaldz26.event_aggregation_service.exceptions;

public class NoAuthorizationToPerformTheAction extends RuntimeException {
    public NoAuthorizationToPerformTheAction(String message) {
        super(message);
    }
}
