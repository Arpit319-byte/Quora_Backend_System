package com.example.Quora_Backend_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuoraBackendSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuoraBackendSystemApplication.class, args);
	}

}
