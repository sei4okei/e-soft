package com.esoft.web.services.impl;

import com.esoft.web.dto.TaskDto;
import com.esoft.web.models.Implementer;
import com.esoft.web.models.Task;
import com.esoft.web.repository.ImplementerRepository;
import com.esoft.web.repository.TaskRepository;
import com.esoft.web.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private ImplementerRepository implementerRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ImplementerRepository implementerRepository) {
        this.taskRepository = taskRepository;
        this.implementerRepository = implementerRepository;
    }

    @Override
    public List<TaskDto> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(this::mapToTaskDto).collect(Collectors.toList());
    }

    @Override
    public void createEvent(Long implementerId, TaskDto taskDto) {
        Task task = mapToTask(taskDto);
        Implementer implementer = implementerRepository.findById(implementerId).get();
        task.setImplementer(implementer);
        taskRepository.save(task);
    }

    private TaskDto mapToTaskDto(Task task) {
        TaskDto taskDto = TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .complexity(task.getComplexity())
                .createdOn(task.getCreatedOn())
                .deadline(task.getDeadline())
                .description(task.getDescription())
                .runtime(task.getRuntime())
                .type(task.getType())
                .build();
        return taskDto;
    }

    private Task mapToTask(TaskDto taskDto) {
        Task task = Task.builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .complexity(taskDto.getComplexity())
                .createdOn(taskDto.getCreatedOn())
                .deadline(taskDto.getDeadline())
                .description(taskDto.getDescription())
                .runtime(taskDto.getRuntime())
                .type(taskDto.getType())
                .build();
        return task;
    }
}
