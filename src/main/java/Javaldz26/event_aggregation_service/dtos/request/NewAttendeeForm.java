package Javaldz26.event_aggregation_service.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewAttendeeForm {

    @NotNull(message = "Nickname can not be null")
    @NotBlank(message = "Nickname must contain at least one non-whitespace character")
    private String attendeeNickname;

    @NotNull(message = "Email can not be null")
    @NotBlank(message = "Email must contain at least one non-whitespace character")
    private String attendeeEmail;


    public String getAttendeeNickname() {
        return attendeeNickname;
    }

    public void setAttendeeNickname(String attendeeNickname) {
        this.attendeeNickname = attendeeNickname;
    }

    public String getAttendeeEmail() {
        return attendeeEmail;
    }

    public void setAttendeeEmail(String attendeeEmail) {
        this.attendeeEmail = attendeeEmail;
    }

    @Override
    public String toString() {
        return "NewAttendeeForm{" +
                "attendeeNickname='" + attendeeNickname + '\'' +
                ", attendeeEmail='" + attendeeEmail + '\'' +
                '}';
    }
}
