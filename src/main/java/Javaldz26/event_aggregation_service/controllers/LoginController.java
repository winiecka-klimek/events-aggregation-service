package Javaldz26.event_aggregation_service.controllers;

import Javaldz26.event_aggregation_service.services.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    private final LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm(){
        return "loginForm";
    }

    @PostMapping("/login-submit-data")
    public String submitLoginForm(@RequestParam String email,
                                  @RequestParam String password) {

        try{
            loginService.loginUser(email, password);
        } catch (Exception e) {
            log.warn("Could not log user in: {}", e.getMessage(), e);
            return "redirect:/login";
        }

        return "redirect:/";
    }
}
