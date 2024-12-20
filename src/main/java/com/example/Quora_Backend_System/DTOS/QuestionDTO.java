package com.example.Quora_Backend_System.DTOS;

import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    private String content;
    private Long userId;
}
