package com.example.Quora_Backend_System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;


    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
}
