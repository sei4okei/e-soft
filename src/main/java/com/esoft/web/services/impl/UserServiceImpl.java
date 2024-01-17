package com.esoft.web.services.impl;

import com.esoft.web.dto.RegistrationDto;
import com.esoft.web.models.Role;
import com.esoft.web.models.UserEntitiy;
import com.esoft.web.repository.RoleRepository;
import com.esoft.web.repository.UserRepository;
import com.esoft.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean saveManager(RegistrationDto registrationDto) {
        if (!checkRegistration(registrationDto)) return false;

        UserEntitiy user = UserEntitiy.builder()
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .username(registrationDto.getUsername())
                .roles(Arrays.asList(roleRepository.findByName("MANAGER")))
                .build();

        try {
            userRepository.save(user);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean saveImplementer(RegistrationDto registrationDto) {
        if (!checkRegistration(registrationDto)) return false;

        UserEntitiy user = UserEntitiy.builder()
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .username(registrationDto.getUsername())
                .roles(Arrays.asList(roleRepository.findByName("IMPLEMENTER")))
                .build();

        try {
            userRepository.save(user);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    private boolean checkRegistration(RegistrationDto registrationDto) {
        UserEntitiy existingUserEmail = findByEmail(registrationDto.getEmail());
        if (existingUserEmail != null
                && existingUserEmail.getEmail() != null
                && !existingUserEmail.getEmail().isEmpty()) {
            return false;
        }

        UserEntitiy existingUsername = findByUsername(registrationDto.getUsername());
        if (existingUsername != null
                && existingUsername.getUsername() != null
                && !existingUsername.getUsername().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public UserEntitiy findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntitiy findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
