package com.esoft.web.repository;

import com.esoft.web.models.Implementer;
import com.esoft.web.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT c from Task c WHERE c.name LIKE CONCAT('%', :query, '%')")
    List<Task> searchTasks(String query);
}
