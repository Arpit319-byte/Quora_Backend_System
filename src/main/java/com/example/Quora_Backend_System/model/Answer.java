package com.example.Quora_Backend_System.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
public class Answer extends BaseClass {

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private Question question;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> comments;

    @OneToOne
    private Report report;


}
