package com.esoft.web.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImplementerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String grade;
    //private List<Task> Tasks;
}
