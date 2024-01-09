package com.esoft.web.repository;

import com.esoft.web.models.Implementer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImplementerRepository extends JpaRepository<Implementer, Long> {
    Optional<Implementer> findByFirstName(String firstName);
    @Query("SELECT c from Implementer c WHERE c.firstName LIKE CONCAT('%', :query, '%')")
    List<Implementer> searchImplementers(String query);
}
