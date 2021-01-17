package Javaldz26.event_aggregation_service.controllers;

import Javaldz26.event_aggregation_service.services.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

    private static final String WRONG_LOGIN_OR_PASSWORD = "Wrong email or/and password";

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "loginForm";
    }

    @GetMapping("/login-error")
    public String invalidCredentials(Model model) {
        model.addAttribute("error", WRONG_LOGIN_OR_PASSWORD);
        return "loginForm";}

}
