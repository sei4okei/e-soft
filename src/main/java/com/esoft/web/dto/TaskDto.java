package com.esoft.web.dto;

import com.esoft.web.enums.TaskType;
import com.esoft.web.models.Implementer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    @NotEmpty(message = "Task name should not be empty")
    private String name;
    @NotEmpty(message = "Task description should not be empty")
    private String description;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
    @NotNull(message = "Task deadline should not be empty")
    private LocalDateTime deadline;
    @NotNull(message = "Task complexity should not be empty")
    private int complexity;
    private int runtime;
    @NotNull(message = "Task type should not be empty")
    private TaskType type;
    @CreationTimestamp
    private LocalDateTime createdOn;
    private Implementer implementer;
}
