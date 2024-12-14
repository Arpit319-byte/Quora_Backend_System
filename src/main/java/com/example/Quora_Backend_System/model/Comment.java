package com.example.Quora_Backend_System.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Comment extends BaseClass {

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParentType parentType;

    @OneToMany(mappedBy = "voteType",cascade = CascadeType.ALL)
    private List<Vote> votes;

    @OneToMany(mappedBy = "targetEntity",cascade = CascadeType.ALL)
    private List<Report> reports;
}
