package Javaldz26.event_aggregation_service.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NewUserRegisteredForEventForm {

    @NotNull(message = "Email can not be null")
    @NotBlank(message = "Email must contain at least one non-whitespace character")
    private String registeredUserNickname;

    @NotNull(message = "Nickname can not be null")
    @NotBlank(message = "Nickname must contain at least one non-whitespace character")
    private String registeredUserEmail;

    private LocalDateTime added =  LocalDateTime.now();

    public LocalDateTime getAdded() {
        return added;
    }

    public void setAdded(LocalDateTime added) {
        this.added = added;
    }

    public String getRegisteredUserEmail() {
        return registeredUserEmail;
    }

    public void setRegisteredUserEmail(String registeredUserEmail) {
        this.registeredUserEmail = registeredUserEmail;
    }

    public String getRegisteredUserNickname() {
        return registeredUserNickname;
    }

    public void setRegisteredUserNickname(String registeredUserNickname) {
        this.registeredUserNickname = registeredUserNickname;
    }
}
