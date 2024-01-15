package com.esoft.web.mapper;

import com.esoft.web.dto.ImplementerDto;
import com.esoft.web.models.Implementer;
import com.esoft.web.models.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.esoft.web.mapper.UserMapper.mapToUser;
import static com.esoft.web.mapper.UserMapper.mapToUserDto;

public class ImplementerMapper {
    public static Implementer mapToImplementer(ImplementerDto implementerDto) {
        Implementer implementer = Implementer.builder()
                .id(implementerDto.getId())
                .firstName(implementerDto.getFirstName())
                .grade(implementerDto.getGrade())
                .lastName(implementerDto.getLastName())
                .patronymic(implementerDto.getPatronymic())
                .tasks(implementerDto.getTasks().stream().map(TaskMapper::mapToTask).collect(Collectors.toList()))
                .user(mapToUser(implementerDto.getUser()))
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
                .user(mapToUserDto(implementer.getUser()))
                .build();
        return  implementerDto;
    }
}
