package com.esoft.web.services.impl;

import com.esoft.web.dto.RegistrationDto;
import com.esoft.web.models.Role;
import com.esoft.web.models.UserEntitiy;
import com.esoft.web.repository.RoleRepository;
import com.esoft.web.repository.UserRepository;
import com.esoft.web.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        Role role = roleRepository.findByName("USER");
        UserEntitiy user = UserEntitiy.builder()
                .email(registrationDto.getEmail())
                .password(registrationDto.getPassword())
                .username(registrationDto.getUsername())
                .roles(Arrays.asList(role))
                .build();
        userRepository.save(user);
    }
}
