package com.example.Quora_Backend_System.DTOS;

import lombok.Data;

@Data
public class AnswerDTO {
    private Long id;
    private String content;
    private Long userId;
    private Long questionId;
}
