package com.esoft.web.controllers;

import com.esoft.web.dto.RegistrationDto;
import com.esoft.web.models.UserEntitiy;
import com.esoft.web.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registerUser(@Valid @ModelAttribute("user") RegistrationDto user,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/register";
        }

        boolean saveResult = userService.saveManager(user);

        if (!saveResult) {
            model.addAttribute("user", user);
            return "/register";
        }

        return "redirect:/implementer?success";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
