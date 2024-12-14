package com.example.Quora_Backend_System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Vote extends BaseClass {

    private Type type;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private TargetEntity targetEntity;

    public enum Type {
        UPVOTE,
        DOWNVOTE
    }

    public enum TargetEntity {
        QUESTION,
        ANSWER,
        COMMENT
    }
}
