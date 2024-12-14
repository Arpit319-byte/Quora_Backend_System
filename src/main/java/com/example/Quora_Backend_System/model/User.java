package com.example.Quora_Backend_System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseClass{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Question> questions;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Answer> answers;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Comment> comments;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Vote> votes;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Report> reports;
}
