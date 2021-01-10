package Javaldz26.event_aggregation_service.controllers;

import Javaldz26.event_aggregation_service.dtos.RegisteredUserDto;
import Javaldz26.event_aggregation_service.dtos.request.NewUserForm;
import Javaldz26.event_aggregation_service.services.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Slf4j
@Controller
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "registerForm";
    }

//    @PostMapping("/register")
//    public String submitRegisterForm(@RequestParam String email,
//                                     @RequestParam String nickname,
//                                     @RequestParam String password) {
//
//        RegisteredUserDto registeredUserDto = registerService.registerUser(email, nickname, password);
//        return "registeredUserThankyouPage";
//    }

    @PostMapping
    public String submitRegisterForm(@ModelAttribute @Valid NewUserForm newUserForm,
                                     BindingResult bindingResult) {

        log.info("New USER: {}", newUserForm);
        log.info("New USER ERRORS: {}", bindingResult.getAllErrors());

        if(bindingResult.hasErrors()) {
            return "registerForm";
        }
        return "registeredUserThankyouPage";
    }
}
