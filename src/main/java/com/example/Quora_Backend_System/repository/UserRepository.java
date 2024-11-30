package com.example.Quora_Backend_System.repository;

import com.example.Quora_Backend_System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
