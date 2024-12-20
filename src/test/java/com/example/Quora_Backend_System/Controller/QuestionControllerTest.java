package com.example.Quora_Backend_System.Controller;

import com.example.Quora_Backend_System.DTO.QuestionDTO;
import com.example.Quora_Backend_System.controller.QuestionController;
import com.example.Quora_Backend_System.model.Question;
import com.example.Quora_Backend_System.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class QuestionControllerTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionController questionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllQuestions() {
        Question question = new Question();
        when(questionService.getAllQuestions()).thenReturn(Collections.singletonList(question));

        ResponseEntity<List<QuestionDTO>> response = questionController.getAllQuestions();

        assertEquals(200, response.getStatusCodeValue());
        verify(questionService, times(1)).getAllQuestions();
    }

    @Test
    void testGetQuestionById() {
        Question question = new Question();
        when(questionService.getQuestionById(1L)).thenReturn(question);

        ResponseEntity<QuestionDTO> response = questionController.getQuestionById(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(questionService, times(1)).getQuestionById(1L);
    }

    @Test
    void testCreateQuestion() {
        Question question = new Question();
        when(questionService.createQuestion(any(Question.class))).thenReturn(question);

        ResponseEntity<QuestionDTO> response = questionController.createQuestion(new QuestionDTO());

        assertEquals(200, response.getStatusCodeValue());
        verify(questionService, times(1)).createQuestion(any(Question.class));
    }

    @Test
    void testUpdateQuestion() {
        Question question = new Question();
        when(questionService.updateQuestion(eq(1L), any(Question.class))).thenReturn(question);

        ResponseEntity<QuestionDTO> response = questionController.updateQuestion(1L, new QuestionDTO());

        assertEquals(200, response.getStatusCodeValue());
        verify(questionService, times(1)).updateQuestion(eq(1L), any(Question.class));
    }

    @Test
    void testDeleteQuestion() {
        when(questionService.deleteQuestion(1L)).thenReturn(true);

        ResponseEntity<?> response = questionController.deleteQuestion(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(questionService, times(1)).deleteQuestion(1L);
    }
}

