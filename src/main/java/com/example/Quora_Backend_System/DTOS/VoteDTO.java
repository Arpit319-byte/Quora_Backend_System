package com.example.Quora_Backend_System.DTOS;

import lombok.Data;

@Data
public class VoteDTO {
    private Long id;
    private Long userId;
    private Long parentId;
    private String parentType;
    private Integer voteType;
}
