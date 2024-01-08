package com.esoft.web.services.impl;

import com.esoft.web.dto.TaskDto;
import com.esoft.web.mapper.TaskMapper;
import com.esoft.web.models.Implementer;
import com.esoft.web.models.Task;
import com.esoft.web.repository.ImplementerRepository;
import com.esoft.web.repository.TaskRepository;
import com.esoft.web.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.esoft.web.mapper.TaskMapper.mapToTask;
import static com.esoft.web.mapper.TaskMapper.mapToTaskDto;

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
        return tasks.stream().map(TaskMapper::mapToTaskDto).collect(Collectors.toList());
    }

    @Override
    public void createTask(Long implementerId, TaskDto taskDto) {
        Task task = mapToTask(taskDto);
        Implementer implementer = implementerRepository.findById(implementerId).get();
        task.setImplementer(implementer);
        taskRepository.save(task);
    }

    @Override
    public TaskDto findTaskById(long id) {
        Task task = taskRepository.findById(id).get();
        return  mapToTaskDto(task);
    }

    @Override
    public void updateTask(TaskDto taskDto) {
        Task task = mapToTask(taskDto);
        taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskDto> searchTasks(String query) {
        List<Task> tasks = taskRepository.searchTasks(query);
        return tasks.stream().map(TaskMapper::mapToTaskDto).collect(Collectors.toList());
    }
}
