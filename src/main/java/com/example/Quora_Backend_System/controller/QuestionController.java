package com.example.Quora_Backend_System.controller;

import com.example.Quora_Backend_System.DTO.QuestionDTO;
import com.example.Quora_Backend_System.model.Question;
import com.example.Quora_Backend_System.service.QuestionService;
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
@RequestMapping("/api/v1/questions")
@Tag(name = "Questions", description = "Operations related to questions")
public class QuestionController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QuestionController.class);

    private final QuestionService questionService;
    private final UserService userService;


    @Autowired
    public QuestionController(QuestionService questionService, UserService userService) {
        this.questionService = questionService;
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get all questions", description = "Fetch a list of all questions")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        logger.info("Getting all questions");
        List<QuestionDTO> questionDTOs = questionService.getAllQuestions().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(questionDTOs);
    }

    @GetMapping("/{questionId}")
    @Operation(summary = "Get question by id", description = "Fetch a question by id")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Long questionId) {
        Question question = questionService.getQuestionById(questionId);
        if (question == null) {
            logger.warn("Question not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Getting question by id - " + questionId);
        return ResponseEntity.ok(convertToDTO(question));
    }

    @PostMapping
    @Operation(summary = "Create a question", description = "Create a question with given Response Body")
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        logger.info("Creating question");
        Question question = convertToEntity(questionDTO);
        Question createdQuestion = questionService.createQuestion(question);
        return ResponseEntity.ok(convertToDTO(createdQuestion));
    }

    @PutMapping("/{questionId}")
    @Operation(summary = "Edit a question", description = "Make changes to a question by given id")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable Long questionId, @Valid @RequestBody QuestionDTO questionDTO) {
        Question question = convertToEntity(questionDTO);
        Question updatedQuestion = questionService.updateQuestion(questionId, question);
        if (updatedQuestion == null) {
            logger.warn("Question not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Updating question");
        return ResponseEntity.ok(convertToDTO(updatedQuestion));
    }

    @DeleteMapping("/{questionId}")
    @Operation(summary = "Delete question by id", description = "Delete a question by id")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        Boolean isDeleted = questionService.deleteQuestion(questionId);
        if (!isDeleted) {
            logger.warn("Question not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Deleting question");
        return ResponseEntity.noContent().build();
    }

    private QuestionDTO convertToDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setContent(question.getContent());
        questionDTO.setUserId(question.getUser().getId());
        return questionDTO;
    }

    private Question convertToEntity(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setContent(questionDTO.getContent());
        question.setUser(userService.getUserById(questionDTO.getUserId()));
        return question;
    }
}