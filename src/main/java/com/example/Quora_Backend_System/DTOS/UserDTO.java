package com.example.Quora_Backend_System.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "User Name cannot be empty")
    @Size(min=2, max=50, message = "User Name must be between 2 and 50 characters")
    private String userName;

    @NotBlank(message = "Password cannot be empty")
    @Size(min=6, max=50, message = "Password must be between 6 and 50 characters")
    private String password;
}
