package com.esoft.web.repository;

import com.esoft.web.models.Implementer;
import com.esoft.web.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
