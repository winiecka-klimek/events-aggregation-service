package Javaldz26.event_aggregation_service.exceptions;

public class UserWithSuchEmailExistsException extends RuntimeException {

    private String email;

    public UserWithSuchEmailExistsException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getMessage() {
        return String.format("User identified by %s already exists.", email);
    }
}
