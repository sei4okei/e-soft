package com.esoft.web.mapper;

import com.esoft.web.dto.ImplementerDto;
import com.esoft.web.dto.RegistrationDto;
import com.esoft.web.models.Implementer;
import com.esoft.web.models.UserEntitiy;
import com.esoft.web.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class ImplementerMapper {
    private static UserService userService;

    @Autowired
    public ImplementerMapper(UserService userService) {
        this.userService = userService;
    }

    public static Implementer mapToImplementer(ImplementerDto implementerDto) {
        Implementer implementer = Implementer.builder()
                .id(implementerDto.getId())
                .firstName(implementerDto.getFirstName())
                .grade(implementerDto.getGrade())
                .lastName(implementerDto.getLastName())
                .patronymic(implementerDto.getPatronymic())
                .tasks(implementerDto.getTasks().stream().map(TaskMapper::mapToTask).collect(Collectors.toList()))
                .user(userService.findByUsername(implementerDto.getUser().getUsername()))
                .build();
        return  implementer;
    }

    public static ImplementerDto mapToImplementerDto(Implementer implementer) {
        ImplementerDto implementerDto = ImplementerDto.builder()
                .id(implementer.getId())
                .firstName(implementer.getFirstName())
                .grade(implementer.getGrade())
                .lastName(implementer.getLastName())
                .patronymic(implementer.getPatronymic())
                .tasks(implementer.getTasks().stream().map(TaskMapper::mapToTaskDto).collect(Collectors.toList()))
                .user(RegistrationDto.builder()
                        .email(implementer.getUser().getEmail())
                        .password(implementer.getUser().getPassword())
                        .username(implementer.getUser().getUsername())
                        .build())
                .build();
        return  implementerDto;
    }
}
