package com.esoft.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ImplementerDto {
    private Long id;
    @NotEmpty(message = "Implementer first name should not be empty")
    private String firstName;
    @NotEmpty(message = "Implementer last name should not be empty")
    private String lastName;
    private String patronymic;
    @NotEmpty(message = "Implementer grade should not be empty")
    private String grade;
    private List<TaskDto> tasks;
}
