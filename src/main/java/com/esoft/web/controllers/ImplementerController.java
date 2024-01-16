package com.esoft.web.controllers;

import com.esoft.web.dto.ImplementerDto;
import com.esoft.web.models.Implementer;
import com.esoft.web.models.UserEntitiy;
import com.esoft.web.services.ImplementerService;
import com.esoft.web.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ImplementerController {
    private UserService userService;
    private ImplementerService implementerService;
    @Autowired
    public ImplementerController(UserService userService, ImplementerService implementerService) {
        this.userService = userService;
        this.implementerService = implementerService;
    }

    @GetMapping("/implementer")
    public String listImplementers(Model model) {
        List<ImplementerDto> implementers= implementerService.findAllImplementers();
        model.addAttribute("implementers", implementers);
        return "implementers-list";
    }

    @GetMapping("/implementer/{implementerId}")
    public String implmenterDetail(@PathVariable("implementerId") long implementerId,
                                       Model model) {
        ImplementerDto implementer= implementerService.findImplementerById(implementerId);
        model.addAttribute("implementer", implementer);
        return "implementers-detail";
    }

    @GetMapping("/implementer/search")
    public String searchImplementer(@RequestParam(value = "query") String query,
                                    Model model) {
        List<ImplementerDto> implementers = implementerService.searchImplementers(query);
        model.addAttribute("implementers", implementers);
        return "implementers-list";
    }

    @GetMapping("implementer/new")
    public String createImplementerForm(Model model) {
        Implementer implemeneter = new Implementer();
        model.addAttribute("implementer", implemeneter);
        return "implementers-create";
    }
    // TO-DO all this logic put in user service
    @PostMapping("implementer/new")
    public String saveImplementer(@Valid @ModelAttribute("implementer") ImplementerDto implementerDto,
                                  BindingResult result,
                                  Model model) {
        UserEntitiy existingUserEmail = userService.findByEmail(implementerDto.getUser().getEmail());
        if (existingUserEmail != null
                && existingUserEmail.getEmail() != null
                && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/implementer/new?fail";
        }

        UserEntitiy existingUsername = userService.findByUsername(implementerDto.getUser().getUsername());

        if (existingUsername != null
                && existingUsername.getUsername() != null
                && !existingUsername.getUsername().isEmpty()) {
            return "redirect:/implementer/new?fail";
        }

        if (result.hasErrors()) {
            model.addAttribute("implementer", implementerDto);
            return "implementers-create";
        }

        userService.saveImplementer(implementerDto.getUser());
        implementerService.saveImplementer(implementerDto);
        return "redirect:/implementer";
    }

    @GetMapping("implementer/{implementerId}/edit")
    public String editImplementerForm(@PathVariable("implementerId") long implementerId, Model model) {
        ImplementerDto implementer = implementerService.findImplementerById(implementerId);
        model.addAttribute("implementer", implementer);
        return "implementers-edit";
    }

    @PostMapping("implementer/{implementerId}/edit")
    public String updateImplmenter(@PathVariable("implementerId") long implementerId,
                                   @Valid @ModelAttribute("implementer") ImplementerDto implementer,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "implementers-edit";
        }
        implementer.setId(implementerId);
        implementerService.updateImplementer(implementer);
        return "redirect:/implementer";
    }

    @PostMapping("implementer/{implementerId}/delete")
    public String deleteImplementer(@PathVariable("implementerId") long implementerId) {
        implementerService.deleteImplementerById(implementerId);
        return "redirect:/implementer";
    }
}
