package com.esoft.web.controllers;

import com.esoft.web.dto.ImplementerDto;
import com.esoft.web.services.ImplementerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
