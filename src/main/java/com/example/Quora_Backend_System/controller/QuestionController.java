package com.example.Quora_Backend_System.controller;

import com.example.Quora_Backend_System.model.Question;
import com.example.Quora_Backend_System.service.QuestionService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")   // This means URL's start with /api/v1/questions after Application path
public class QuestionController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QuestionController.class);

    // This means to get the bean called questionService
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        logger.info("Getting all questions");
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long questionId) {

        Question question = questionService.getQuestionById(questionId);

        if (question == null) {
            logger.warn("Question not found");
            return ResponseEntity.notFound().build();
        }

        logger.info("Getting question by id - " + questionId);
        return ResponseEntity.ok(question);
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@Valid @RequestBody Question question) {
        logger.info("Creating question");
        return ResponseEntity.ok(questionService.createQuestion(question));
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long questionId,@Valid @RequestBody Question question) {

            Question updatedQuestion = questionService.updateQuestion(questionId, question);

            if(updatedQuestion == null) {
                logger.warn("Question not found");
                return ResponseEntity.notFound().build();
            }

            logger.info("Updating question");
            return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        Boolean isDeleted = questionService.deleteQuestion(questionId);

        if (!isDeleted) {
            logger.warn("Question not found");
            ResponseEntity.notFound().build();
        }

        logger.info("Deleting question");
        return ResponseEntity.noContent().build();
    }
}
