package Javaldz26.event_aggregation_service.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewUserForm {

    @NotNull(message = "Email can not be empty")
    @NotBlank(message = "Email can not be empty")
    @Size(max = 100)
    private String email;

    @NotNull(message = "Nickname can not be empty")
    @NotBlank(message = "Nickname can not be empty")
    private String nickname;

    @Size(min = 8, max = 30)
    @NotNull(message = "Password can not be empty")
    @NotBlank(message = "Password can not be empty")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NewUserForm{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
