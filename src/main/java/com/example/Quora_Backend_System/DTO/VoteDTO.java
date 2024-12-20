package com.example.Quora_Backend_System.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VoteDTO {

    private Long id;

    @NotBlank(message = "Vote type is required")
    @Size(max = 10, message = "Vote type can't be more than 10 characters")
    private String voteType;

    @NotNull(message = "User id is required")
    private Long userId;


    private Long answerId;
    private Long questionId;
    private Long commentId;
}
