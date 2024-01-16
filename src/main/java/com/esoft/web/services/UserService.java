package com.esoft.web.services;

import com.esoft.web.dto.RegistrationDto;
import com.esoft.web.models.UserEntitiy;

public interface UserService {
    void saveManager(RegistrationDto registrationDto);
    void saveImplementer(RegistrationDto registrationDto);
    UserEntitiy findByUsername(String username);
    UserEntitiy findByEmail(String email);
}
