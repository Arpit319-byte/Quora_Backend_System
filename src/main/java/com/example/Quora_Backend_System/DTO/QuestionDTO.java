package com.example.Quora_Backend_System.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuestionDTO {

    private Long id;

    @NotBlank(message = "Question Content cannot be empty")
    @Size(min=2, max=3000, message = "Question Content must be between 2 and 3000 characters")
    private String content;

    @NotNull(message = "User Id cannot be empty")
    private Long userId;
}
