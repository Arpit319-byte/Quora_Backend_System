package com.example.Quora_Backend_System.controller;

import com.example.Quora_Backend_System.model.Answer;
import com.example.Quora_Backend_System.service.AnswerService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answers")
public class AnswerController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AnswerController.class);

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    public ResponseEntity<List<Answer>> getAllAnswers() {
        logger.info("Getting all answers");
        return ResponseEntity.ok(answerService.getAllAnswers());
    }

    @GetMapping("/{answerId}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long answerId) {
        Answer answer = answerService.getAnswerById(answerId);
        if (answer == null) {
            logger.warn("Answer not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Getting answer by id - " + answerId);
        return ResponseEntity.ok(answer);
    }

    @PostMapping
    public ResponseEntity<Answer> createAnswer(@Valid @RequestBody Answer answer) {
        logger.info("Creating answer");
        return ResponseEntity.ok(answerService.createAnswer(answer));
    }

    @PutMapping("/{answerId}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long answerId, @RequestBody Answer answer) {
        Answer updatedAnswer = answerService.updateAnswer(answerId, answer);
        if (updatedAnswer == null) {
            logger.warn("Answer not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Updating answer");
        return ResponseEntity.ok(updatedAnswer);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long answerId) {
        Boolean isDeleted = answerService.deleteAnswer(answerId);
        if (!isDeleted) {
            logger.warn("Answer not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Deleting answer");
        return ResponseEntity.ok().build();
    }

}
