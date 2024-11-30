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

    @Column(nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

}
