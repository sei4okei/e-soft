package com.esoft.web.services.impl;

import com.esoft.web.dto.ImplementerDto;
import com.esoft.web.models.Implementer;
import com.esoft.web.repository.ImplementerRepository;
import com.esoft.web.services.ImplementerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Implementer saveImplementer(ImplementerDto implementerDto) {
        Implementer implementer = mapToImplementer(implementerDto);
        return implementerRepository.save(implementer);
    }

    @Override
    public ImplementerDto findImplementerById(long id) {
        Implementer implementer = implementerRepository.findById(id).get();
        return mapToImplementerDto(implementer);
    }

    @Override
    public void updateImplementer(ImplementerDto implementerDto) {
        Implementer implementer = mapToImplementer(implementerDto);
        implementerRepository.save(implementer);
    }

    @Override
    public void deleteImplementerById(long implementerId) {
        implementerRepository.deleteById(implementerId);
    }

    @Override
    public List<ImplementerDto> searchImplementers(String query) {
        List<Implementer> implementers = implementerRepository.searchImplementers(query);
        return implementers.stream().map(this::mapToImplementerDto).collect(Collectors.toList());
    }

    private Implementer mapToImplementer(ImplementerDto implementerDto) {
        Implementer implementer = Implementer.builder()
                .id(implementerDto.getId())
                .firstName(implementerDto.getFirstName())
                .grade(implementerDto.getGrade())
                .lastName(implementerDto.getLastName())
                .patronymic(implementerDto.getPatronymic())
                .build();
        return  implementer;
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
