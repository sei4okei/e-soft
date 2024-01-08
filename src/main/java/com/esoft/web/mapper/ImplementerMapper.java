package com.esoft.web.mapper;

import com.esoft.web.dto.ImplementerDto;
import com.esoft.web.models.Implementer;

public class ImplementerMapper {
    public static Implementer mapToImplementer(ImplementerDto implementerDto) {
        Implementer implementer = Implementer.builder()
                .id(implementerDto.getId())
                .firstName(implementerDto.getFirstName())
                .grade(implementerDto.getGrade())
                .lastName(implementerDto.getLastName())
                .patronymic(implementerDto.getPatronymic())
                .tasks(implementerDto.getTasks())
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
                .tasks(implementer.getTasks())
                .build();
        return  implementerDto;
    }
}
