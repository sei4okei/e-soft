package com.esoft.web.controllers;

import com.esoft.web.dto.TaskDto;
import com.esoft.web.models.Implementer;
import com.esoft.web.models.Task;
import com.esoft.web.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@Controller
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task")
    public String taskList(Model model) {
        List<TaskDto> tasks = taskService.findAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks-list";
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
        taskService.createTask(implementerId, taskDto);
        return "redirect:/implementer/" + implementerId;
    }

    @PostMapping("/task/{taskId}/delete")
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
}
