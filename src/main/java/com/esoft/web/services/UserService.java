package com.esoft.web.services;

import com.esoft.web.dto.RegistrationDto;
import com.esoft.web.models.UserEntitiy;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntitiy findByUsername(String username);
    UserEntitiy findByEmail(String email);
}
