package com.example.Quora_Backend_System.service;

import com.example.Quora_Backend_System.model.User;
import com.example.Quora_Backend_System.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        logger.info("Getting all users from the database");
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while getting all users from the database", e);
            return null;
        }
    }

    public User getUserById(Long userId) {
        logger.info("Getting user by id - {}", userId);
        try {
            return userRepository.findById(userId).orElse(null);
        } catch (Exception e) {
            logger.error("Error occurred while getting user by id - " + userId, e);
            return null;
        }
    }

    @Transactional
    public User createUser(User user) {
        logger.info("Creating user in the database");
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            logger.error("Error occurred while creating user in the database", e);
            return null;
        }
    }

    @Transactional
    public User updateUser(Long userId, User user) {
        logger.info("Updating user by id - {}", userId);
        try {
            User userToUpdate = userRepository.findById(userId).orElse(null);
            if (userToUpdate == null) {
                logger.error("User not found with id {}", userId);
                return null;
            }
            userToUpdate.setUserName(user.getUserName());
            userToUpdate.setEmail(user.getEmail());
            return userRepository.save(userToUpdate);
        } catch (Exception e) {
            logger.error("Error occurred while updating user by id - " + userId, e);
            return null;
        }
    }

    @Transactional
    public Boolean deleteUser(Long userId) {
        logger.info("Deleting user by id - {}", userId);
        try {
            User userToDelete = userRepository.findById(userId).orElse(null);
            if (userToDelete == null) {
                logger.error("User not found with id {}", userId);
                return false;
            }
            userRepository.delete(userToDelete);
            return true;
        } catch (Exception e) {
            logger.error("Error occurred while deleting user by id - " + userId, e);
            return false;
        }
    }
}