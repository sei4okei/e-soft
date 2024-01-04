package com.esoft.web.controllers;

import com.esoft.web.dto.ImplementerDto;
import com.esoft.web.models.Implementer;
import com.esoft.web.services.ImplementerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ImplementerController {
    private ImplementerService implementerService;
    @Autowired
    public ImplementerController(ImplementerService implementerService) {
        this.implementerService = implementerService;
    }

    @GetMapping("/implementer")
    public String listImplementers(Model model) {
        List<ImplementerDto> implementers= implementerService.findAllImplementers();
        model.addAttribute("implementers", implementers);
        return "implementers-list";
    }

    @GetMapping("implementer/new")
    public String createImplementerForm(Model model) {
        Implementer implemeneter = new Implementer();
        model.addAttribute("implementer", implemeneter);
        return "implementers-create";
    }

    @PostMapping("implementer/new")
    public String saveImplementer(@ModelAttribute("implementer") Implementer implementer) {
        implementerService.saveImplementer(implementer);
        return "redirect:/implementer";
    }

    @GetMapping("implementer/{implementerId}/edit")
    public String editImplementerForm(@PathVariable("implementerId") long implementerId, Model model) {
        ImplementerDto implementer = implementerService.findImplementerById(implementerId);
        model.addAttribute("implementer", implementer);
        return "implementers-edit";
    }

    @PostMapping("implementer/{implementerId}/edit")
    public String updateImplmenter(@PathVariable("implementerId") long implementerId, @ModelAttribute("implementer") ImplementerDto implementer) {
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
