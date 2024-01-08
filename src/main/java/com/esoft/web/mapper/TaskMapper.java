package com.esoft.web.mapper;

import com.esoft.web.dto.TaskDto;
import com.esoft.web.models.Task;

public class TaskMapper {
    public static TaskDto mapToTaskDto(Task task) {
        TaskDto taskDto = TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .complexity(task.getComplexity())
                .createdOn(task.getCreatedOn())
                .deadline(task.getDeadline())
                .description(task.getDescription())
                .runtime(task.getRuntime())
                .type(task.getType())
                .implementer(task.getImplementer())
                .build();
        return taskDto;
    }

    public static Task mapToTask(TaskDto taskDto) {
        Task task = Task.builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .complexity(taskDto.getComplexity())
                .createdOn(taskDto.getCreatedOn())
                .deadline(taskDto.getDeadline())
                .description(taskDto.getDescription())
                .runtime(taskDto.getRuntime())
                .type(taskDto.getType())
                .implementer(taskDto.getImplementer())
                .build();
        return task;
    }
}
