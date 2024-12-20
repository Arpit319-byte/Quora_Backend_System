package com.example.Quora_Backend_System.DTOS;

import lombok.Data;

@Data
public class ReportDTO {
    private Long id;
    private Long userId;
    private Long answerId;
    private Long questionId;
    private String content;
    private String type;
    private String status;
}
