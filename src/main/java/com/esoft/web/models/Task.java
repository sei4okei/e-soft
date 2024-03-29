package com.esoft.web.models;

import com.esoft.web.enums.TaskType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
    private LocalDateTime deadline;
    private int complexity;
    private int runtime;
    private TaskType type;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntitiy createdBy;

    @ManyToOne
    @JoinColumn(name = "implementer_id", nullable = false)
    private Implementer implementer;
}
