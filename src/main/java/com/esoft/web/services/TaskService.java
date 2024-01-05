package com.esoft.web.services;

import com.esoft.web.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> findAllTasks();
    void createEvent(Long implementerId, TaskDto taskDto);

}
