package com.example.SirmaTask.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Project {
    @Id
    @Column(nullable = false)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
}
