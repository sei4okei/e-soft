package com.esoft.web.repository;

import com.esoft.web.models.UserEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntitiy, Long> {
    UserEntitiy findByEmail(String email);
    UserEntitiy findByUsername(String username);
    UserEntitiy findFirstByUsername(String username);
}
