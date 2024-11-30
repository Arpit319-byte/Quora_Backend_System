package com.example.Quora_Backend_System.model;

import jakarta.persistence.*;
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

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

//    @OneToMany(mappedBy = "question")
//    private List<Comment> comments;

}
