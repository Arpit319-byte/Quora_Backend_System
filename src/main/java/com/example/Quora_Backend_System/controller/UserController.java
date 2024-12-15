package com.example.Quora_Backend_System.controller;

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
import java.util.logging.Logger;

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
    @Operation(summary = "Get all questions", description = "Fetch a list of all user")
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Getting all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by id", description = "Fetch a  user by id")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {

        User user = userService.getUserById(userId);
        if (user == null) {
            logger.warn("User not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Getting user by id - " + userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "Create a user", description = "Create a user with given Response Body")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        logger.info("Creating user");
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update a user", description = "Update a user by given id")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @Valid @RequestBody User user) {

        User updatedUser = userService.updateUser(userId, user);
        if(updatedUser == null) {
            logger.warn("User not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Updating user");
        return ResponseEntity.ok(updatedUser);
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
}
