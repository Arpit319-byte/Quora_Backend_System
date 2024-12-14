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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Answer extends BaseClass {

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "answer",cascade = CascadeType.ALL)
    private List<Comment> comment;

    @OneToMany(mappedBy = "VoteType",cascade = CascadeType.ALL)
    private List<Vote> votes;

    @OneToMany(mappedBy = "report",cascade = CascadeType.ALL)
    private List<Report> reports;
}
