package com.example.Quora_Backend_System.DTOS;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private Long userId;
    private Long answerId;
    private Long parentCommentId;
}
