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

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<Answer> answers;

    @OneToMany(mappedBy = "parentType",cascade = CascadeType.ALL)
    List<Comment> comments;

    @OneToMany(mappedBy = "targetEntity",cascade = CascadeType.ALL)
    List<Report> reports;

}
