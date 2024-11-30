package com.example.Quora_Backend_System.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Question extends BaseClass {

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @OneToOne
    private User user;

    @ManyToOne
    private List<Answer> answers;

    @ManyToOne
    private List<Comment> comments;

}
