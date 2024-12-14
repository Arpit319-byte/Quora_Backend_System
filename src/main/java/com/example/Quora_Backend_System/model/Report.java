package com.example.Quora_Backend_System.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Schema(description = "Details about the Report")
public class Report extends BaseClass{

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TargetEntity targetEntity;

    @Column(nullable = false)
    private Long targetId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


}
