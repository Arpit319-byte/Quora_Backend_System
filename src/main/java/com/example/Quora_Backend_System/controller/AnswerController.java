package com.example.Quora_Backend_System.controller;

import com.example.Quora_Backend_System.DTO.AnswerDTO;
import com.example.Quora_Backend_System.model.Answer;
import com.example.Quora_Backend_System.service.AnswerService;
import com.example.Quora_Backend_System.service.QuestionService;
import com.example.Quora_Backend_System.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/answers")
@Tag(name = "Answers", description = "Operations related to answer")
public class AnswerController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AnswerController.class);

    private final AnswerService answerService;
    private final UserService userService;
    private final QuestionService questionService;

    public AnswerController(AnswerService answerService, UserService userService, QuestionService questionService) {
        this.answerService = answerService;
        this.userService = userService;
        this.questionService = questionService;
    }

    @GetMapping
    @Operation(summary = "Get all answers", description = "Fetch a list of all answers")
    public ResponseEntity<List<AnswerDTO>> getAllAnswers() {
        logger.info("Getting all answers");
        List<AnswerDTO> answerDTOs = answerService.getAllAnswers().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(answerDTOs);
    }

    @GetMapping("/{answerId}")
    @Operation(summary = "Get answer by id", description = "Fetch an answer by id")
    public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable Long answerId) {
        Answer answer = answerService.getAnswerById(answerId);
        if (answer == null) {
            logger.warn("Answer not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Getting answer by id - " + answerId);
        return ResponseEntity.ok(convertToDTO(answer));
    }

    @PostMapping
    @Operation(summary = "Create an answer", description = "Create an answer with given Response Body")
    public ResponseEntity<AnswerDTO> createAnswer(@Valid @RequestBody AnswerDTO answerDTO) {
        logger.info("Creating answer");
        Answer answer = convertToEntity(answerDTO);
        Answer createdAnswer = answerService.createAnswer(answer);
        return ResponseEntity.ok(convertToDTO(createdAnswer));
    }

    @PutMapping("/{answerId}")
    @Operation(summary = "Update an answer", description = "Update an answer by given id")
    public ResponseEntity<AnswerDTO> updateAnswer(@PathVariable Long answerId, @Valid @RequestBody AnswerDTO answerDTO) {
        Answer answer = convertToEntity(answerDTO);
        Answer updatedAnswer = answerService.updateAnswer(answerId, answer);
        if (updatedAnswer == null) {
            logger.warn("Answer not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Updating answer");
        return ResponseEntity.ok(convertToDTO(updatedAnswer));
    }

    @DeleteMapping("/{answerId}")
    @Operation(summary = "Delete an answer", description = "Delete an answer by given id")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long answerId) {
        Boolean isDeleted = answerService.deleteAnswer(answerId);
        if (!isDeleted) {
            logger.warn("Answer not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Deleting answer");
        return ResponseEntity.noContent().build();
    }

    private AnswerDTO convertToDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(answer.getId());
        answerDTO.setContent(answer.getContent());
        answerDTO.setUserId(answer.getUser().getId());
        answerDTO.setQuestionId(answer.getQuestion().getId());
        return answerDTO;
    }

    private Answer convertToEntity(AnswerDTO answerDTO) {
        Answer answer = new Answer();
        answer.setId(answerDTO.getId());
        answer.setContent(answerDTO.getContent());
        answer.setUser(userService.getUserById(answerDTO.getUserId()));
        answer.setQuestion(questionService.getQuestionById(answerDTO.getQuestionId()));
        return answer;
    }
}