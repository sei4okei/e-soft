package com.esoft.web.services.impl;

import com.esoft.web.dto.ImplementerDto;
import com.esoft.web.models.Implementer;
import com.esoft.web.repository.ImplementerRepository;
import com.esoft.web.services.ImplementerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImplementerServiceImpl implements ImplementerService {
    private ImplementerRepository implementerRepository;

    @Autowired
    public ImplementerServiceImpl(ImplementerRepository implementerRepository) {
        this.implementerRepository = implementerRepository;
    }

    @Override
    public List<ImplementerDto> findAllImplementers() {
        List<Implementer> listImplementer = implementerRepository.findAll();
        return listImplementer.stream().map(this::mapToImplementerDto).collect(Collectors.toList());
    }

    private ImplementerDto mapToImplementerDto(Implementer implementer) {
        ImplementerDto implementerDto = ImplementerDto.builder()
                .id(implementer.getId())
                .firstName(implementer.getFirstName())
                .grade(implementer.getGrade())
                .lastName(implementer.getLastName())
                .patronymic(implementer.getPatronymic())
                .build();
        return  implementerDto;
    }
}
