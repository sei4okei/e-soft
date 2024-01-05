package com.esoft.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.thymeleaf.spring6.processor.SpringOptionFieldTagProcessor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "implementer")
public class Implementer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String grade;

    @OneToMany(mappedBy = "implementer", cascade = CascadeType.REMOVE)
    private Set<Task> tasks = new HashSet<>();
}
