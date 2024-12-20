package com.example.Quora_Backend_System.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Schema(description = "Details about the Question")
public class Question extends BaseClass {

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


}
