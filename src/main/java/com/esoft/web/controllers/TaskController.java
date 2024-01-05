package com.esoft.web.controllers;

import com.esoft.web.dto.TaskDto;
import com.esoft.web.models.Task;
import com.esoft.web.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;

@Controller
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task/{implementerId}/new")
    public String createTaskForm(@PathVariable("implementerId") Long implementerId,
                                 Model model) {
        TaskDto task = new TaskDto();
        model.addAttribute("implementerId", implementerId);
        model.addAttribute("task", task);
        return "tasks-create";
    }

    @PostMapping("/task/{implementerId}")
    public String createTask(@PathVariable("implementerId") Long implementerId,
                             @Valid @ModelAttribute("task") TaskDto taskDto,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()){
            model.addAttribute("task", taskDto);
            return "tasks-create";
        }
        taskService.createEvent(implementerId, taskDto);
        return "redirect:/implementer/" + implementerId;
    }
}
