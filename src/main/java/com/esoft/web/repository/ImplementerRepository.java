package com.esoft.web.repository;

import com.esoft.web.models.Implementer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImplementerRepository extends JpaRepository<Implementer, Long> {
    Optional<Implementer> findByFirstName(String firstName);
}
