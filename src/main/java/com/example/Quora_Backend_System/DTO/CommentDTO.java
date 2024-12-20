package com.example.Quora_Backend_System.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDTO {

    private Long id;

    @NotBlank(message = "Comment Content cannot be empty")
    @Size(min=2, max=3000, message = "Comment Content must be between 2 and 3000 characters")
    private String content;

    @NotNull
    private Long userId;

    @NotNull
    private Long answerId;
    private Long parentCommentId;
}
