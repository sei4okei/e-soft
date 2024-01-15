package com.esoft.web.controllers;

import com.esoft.web.dto.ImplementerDto;
import com.esoft.web.models.Implementer;
import com.esoft.web.models.UserEntitiy;
import com.esoft.web.services.ImplementerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@PreAuthorize("hasAuthority('MANAGER')")
public class ImplementerController {
    private ImplementerService implementerService;
    @Autowired
    public ImplementerController(ImplementerService implementerService) {
        this.implementerService = implementerService;
    }

    @GetMapping("/implementer")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public String listImplementers(Model model) {
        List<ImplementerDto> implementers= implementerService.findAllImplementers();
        model.addAttribute("implementers", implementers);
        return "implementers-list";
    }

    @GetMapping("/implementer/{implementerId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String implmenterDetail(@PathVariable("implementerId") long implementerId,
                                       Model model) {
        ImplementerDto implementer= implementerService.findImplementerById(implementerId);
        model.addAttribute("implementer", implementer);
        return "implementers-detail";
    }

    @GetMapping("/implementer/search")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String searchImplementer(@RequestParam(value = "query") String query,
                                    Model model) {
        List<ImplementerDto> implementers = implementerService.searchImplementers(query);
        model.addAttribute("implementers", implementers);
        return "implementers-list";
    }

    @GetMapping("implementer/new")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String createImplementerForm(Model model) {
        Implementer implemeneter = new Implementer();
        model.addAttribute("implementer", implemeneter);
        return "implementers-create";
    }
    // TO-DO add implementer creation with linked account
    @PostMapping("implementer/new")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String saveImplementer(@Valid @ModelAttribute("implementer") ImplementerDto implementerDto,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("implementer", implementerDto);
            return "implementers-create";
        }
        implementerService.saveImplementer(implementerDto);
        return "redirect:/implementer";
    }

    @GetMapping("implementer/{implementerId}/edit")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String editImplementerForm(@PathVariable("implementerId") long implementerId, Model model) {
        ImplementerDto implementer = implementerService.findImplementerById(implementerId);
        model.addAttribute("implementer", implementer);
        return "implementers-edit";
    }

    @PostMapping("implementer/{implementerId}/edit")
    @PreAuthorize("hasAuthority('MANAGER')")
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
    @PreAuthorize("hasAuthority('MANAGER')")
    public String deleteImplementer(@PathVariable("implementerId") long implementerId) {
        implementerService.deleteImplementerById(implementerId);
        return "redirect:/implementer";
    }
}
