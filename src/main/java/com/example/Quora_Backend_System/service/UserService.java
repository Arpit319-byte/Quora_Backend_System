package com.example.Quora_Backend_System.service;

import com.example.Quora_Backend_System.model.User;
import com.example.Quora_Backend_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User user) {
        return userRepository.save(user);
    }

    public Boolean deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
