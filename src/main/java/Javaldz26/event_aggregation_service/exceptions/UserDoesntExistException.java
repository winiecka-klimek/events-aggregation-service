package Javaldz26.event_aggregation_service.exceptions;

public class UserDoesntExistException extends RuntimeException {

    private String email;

    public UserDoesntExistException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return String.format("User identified by %s could't be found.", email);
    }

    public String getEmail() {
        return email;
    }
}
