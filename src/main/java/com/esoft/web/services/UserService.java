package com.esoft.web.services;

import com.esoft.web.dto.RegistrationDto;
import com.esoft.web.models.UserEntitiy;

public interface UserService {
    boolean saveManager(RegistrationDto registrationDto);
    boolean saveImplementer(RegistrationDto registrationDto);
    UserEntitiy findByUsername(String username);
    UserEntitiy findByEmail(String email);
}
