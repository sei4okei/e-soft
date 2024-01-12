package com.esoft.web.controllers;

import com.esoft.web.dto.TaskDto;
import com.esoft.web.models.UserEntitiy;
import com.esoft.web.security.SecurityUtil;
import com.esoft.web.services.TaskService;
import com.esoft.web.services.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    private TaskService taskService;
    private UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/task")
    public String taskList(Model model) {
        UserEntitiy user = new UserEntitiy();
        List<TaskDto> tasks = taskService.findAllTasks();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
        }
        model.addAttribute("user", user);
        model.addAttribute("tasks", tasks);
        return "tasks-list";
    }


    @GetMapping("/task/{implementerId}/new")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public String createTaskForm(@PathVariable("implementerId") Long implementerId,
                                 Model model) {
        TaskDto task = new TaskDto();
        model.addAttribute("implementerId", implementerId);
        model.addAttribute("task", task);
        return "tasks-create";
    }


    @PostMapping("/task/{implementerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public String createTask(@PathVariable("implementerId") Long implementerId,
                             @Valid @ModelAttribute("task") TaskDto taskDto,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()){
            model.addAttribute("task", taskDto);
            return "tasks-create";
        }
        taskService.createTask(implementerId, taskDto);
        return "redirect:/implementer/" + implementerId;
    }


    @PostMapping("/task/{taskId}/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public String deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTaskById(taskId);
        return "redirect:/task";
    }

    @GetMapping("/task/{taskId}/edit")
    public String editTaskForm(@PathVariable("taskId") Long taskId,
                           Model model) {
        TaskDto task = taskService.findTaskById(taskId);
        model.addAttribute("task", task);
        return "tasks-edit";
    }

    @PostMapping("/task/{taskId}/edit")
    public String editTask(@PathVariable("taskId") Long taskId,
                           @Valid @ModelAttribute("task") TaskDto task,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("task", task);
            return "tasks-edit";
        }
        TaskDto taskDto = taskService.findTaskById(taskId);
        task.setId(taskId);
        task.setImplementer(taskDto.getImplementer());
        taskService.updateTask(task);
        return "redirect:/task";
    }

    @GetMapping("/task/search")
    public String searchTasks(@RequestParam("query") String query,
                              Model model) {
        List<TaskDto> tasks = taskService.searchTasks(query);
        model.addAttribute("tasks", tasks);
        return "tasks-list";
    }

    @GetMapping("/task/{taskId}")
    public String detailTask(@PathVariable("taskId") Long taskId,
                             Model model) {
        UserEntitiy user = new UserEntitiy();
        TaskDto task = taskService.findTaskById(taskId);
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
        }
        model.addAttribute("user", user);
        model.addAttribute("task", task);
        return "tasks-detail";
    }
}
