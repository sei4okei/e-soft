package com.esoft.web.services.impl;

import com.esoft.web.dto.ImplementerDto;
import com.esoft.web.mapper.ImplementerMapper;
import com.esoft.web.models.Implementer;
import com.esoft.web.repository.ImplementerRepository;
import com.esoft.web.repository.UserRepository;
import com.esoft.web.services.ImplementerService;
import com.esoft.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.esoft.web.mapper.ImplementerMapper.mapToImplementer;
import static com.esoft.web.mapper.ImplementerMapper.mapToImplementerDto;

@Service
public class ImplementerServiceImpl implements ImplementerService {
    private ImplementerRepository implementerRepository;
    private UserService userService;

    @Autowired
    public ImplementerServiceImpl(ImplementerRepository implementerRepository, UserRepository userRepository, UserService userService) {
        this.implementerRepository = implementerRepository;
        this.userService = userService;
    }

    @Override
    public List<ImplementerDto> findAllImplementers() {
        List<Implementer> listImplementer = implementerRepository.findAll();
        return listImplementer.stream().map(ImplementerMapper::mapToImplementerDto).collect(Collectors.toList());
    }

    @Override
    public Implementer saveImplementer(ImplementerDto implementerDto) {
        implementerDto.setTasks(new ArrayList<>());
        userService.saveUser(implementerDto.getUser());
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
        return implementers.stream().map(ImplementerMapper::mapToImplementerDto).collect(Collectors.toList());
    }
}
