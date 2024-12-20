package com.example.Quora_Backend_System.controller;

import com.example.Quora_Backend_System.DTO.UserDTO;
import com.example.Quora_Backend_System.model.User;
import com.example.Quora_Backend_System.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "Operations related to User")
public class UserController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Fetch a list of all users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        logger.info("Getting all users");
        List<UserDTO> userDTOs = userService.getAllUsers().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by id", description = "Fetch a user by id")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            logger.warn("User not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Getting user by id - " + userId);
        return ResponseEntity.ok(convertToDTO(user));
    }

    @PostMapping
    @Operation(summary = "Create a user", description = "Create a user with given Response Body")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        logger.info("Creating user");
        User user = convertToEntity(userDTO);
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(convertToDTO(createdUser));
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update a user", description = "Update a user by given id")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @Valid @RequestBody UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User updatedUser = userService.updateUser(userId, user);
        if (updatedUser == null) {
            logger.warn("User not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Updating user");
        return ResponseEntity.ok(convertToDTO(updatedUser));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a user", description = "Delete a user by given id")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        Boolean isDeleted = userService.deleteUser(userId);
        if (!isDeleted) {
            logger.warn("User not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Deleting user");
        return ResponseEntity.noContent().build();
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}