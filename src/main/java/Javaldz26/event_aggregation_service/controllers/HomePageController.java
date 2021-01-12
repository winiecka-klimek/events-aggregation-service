package Javaldz26.event_aggregation_service.controllers;

import Javaldz26.event_aggregation_service.services.LoginService;
import Javaldz26.event_aggregation_service.services.UserContextService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    private final LoginService loginService;
    private final UserContextService userContextService;

    public HomePageController(LoginService loginService, UserContextService userContextService) {
        this.loginService = loginService;
        this.userContextService = userContextService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {

        model.addAttribute("userLogged", loginService.isLogged());
        model.addAttribute("userInfo", loginService.getUserSessionDto());
        model.addAttribute("loggedAs", userContextService.getCurrentlyLoggedUserEmail());


        return "homePage";
    }

}
