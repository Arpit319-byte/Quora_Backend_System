package com.example.Quora_Backend_System.controller;

import com.example.Quora_Backend_System.model.Answer;
import com.example.Quora_Backend_System.service.AnswerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/{answerId}")
    public Answer getAnswerById(@PathVariable Long answerId) {
        return answerService.getAnswerById(answerId);
    }

    @PostMapping
    public Answer createAnswer(@RequestBody Answer answer) {
        return answerService.createAnswer(answer);
    }

    @PutMapping("/{answerId}")
    public Answer updateAnswer(@PathVariable Long answerId, @RequestBody Answer answer) {
        return answerService.updateAnswer(answerId, answer);
    }

    @DeleteMapping("/{answerId}")
    public void deleteAnswer(@PathVariable Long answerId) {
        answerService.deleteAnswer(answerId);
    }

}
