package com.techreturners.todolistapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Task {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotBlank(message = "Title cannot be null or empty")
    @Column
    private String title;

    @NotBlank(message = "Description cannot be null or empty")
    @Column
    private String description;

    @NotNull
    @Column
    private boolean completed;
}
