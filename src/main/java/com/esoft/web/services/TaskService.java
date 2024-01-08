package com.esoft.web.services;

import com.esoft.web.dto.ImplementerDto;
import com.esoft.web.dto.TaskDto;
import com.esoft.web.models.Task;

import java.util.List;

public interface TaskService {
    List<TaskDto> findAllTasks();
    void createTask(Long implementerId, TaskDto taskDto);
    TaskDto findTaskById(long id);
    void updateTask(TaskDto taskDto);
    void deleteTaskById(long taskId);
    List<TaskDto> searchTasks(String query);


}
