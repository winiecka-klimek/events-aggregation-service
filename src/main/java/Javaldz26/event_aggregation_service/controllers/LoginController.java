package Javaldz26.event_aggregation_service.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class LoginController {

    private static final String WRONG_LOGIN_OR_PASSWORD = "Wrong email or/and password";

    @GetMapping("/login")
    public String showLoginForm(){
        return "loginForm";
    }

    @GetMapping("/login-error")
    public String invalidCredentials(Model model) {
        model.addAttribute("error", WRONG_LOGIN_OR_PASSWORD);
        return "loginForm";}

}
