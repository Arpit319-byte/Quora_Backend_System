package com.example.Quora_Backend_System.Controller;


import com.example.Quora_Backend_System.DTO.AnswerDTO;
import com.example.Quora_Backend_System.controller.AnswerController;
import com.example.Quora_Backend_System.model.Answer;
import com.example.Quora_Backend_System.service.AnswerService;
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

class AnswerControllerTest {

    @Mock
    private AnswerService answerService;

    @InjectMocks
    private AnswerController answerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAnswers() {
        Answer answer = new Answer();
        when(answerService.getAllAnswers()).thenReturn(Collections.singletonList(answer));

        ResponseEntity<List<AnswerDTO>> response = answerController.getAllAnswers();

        assertEquals(200, response.getStatusCodeValue());
        verify(answerService, times(1)).getAllAnswers();
    }

    @Test
    void testGetAnswerById() {
        Answer answer = new Answer();
        when(answerService.getAnswerById(1L)).thenReturn(answer);

        ResponseEntity<AnswerDTO> response = answerController.getAnswerById(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(answerService, times(1)).getAnswerById(1L);
    }

    @Test
    void testCreateAnswer() {
        Answer answer = new Answer();
        when(answerService.createAnswer(any(Answer.class))).thenReturn(answer);

        ResponseEntity<AnswerDTO> response = answerController.createAnswer(new AnswerDTO());

        assertEquals(200, response.getStatusCodeValue());
        verify(answerService, times(1)).createAnswer(any(Answer.class));
    }

    @Test
    void testUpdateAnswer() {
        Answer answer = new Answer();
        when(answerService.updateAnswer(eq(1L), any(Answer.class))).thenReturn(answer);

        ResponseEntity<AnswerDTO> response = answerController.updateAnswer(1L, new AnswerDTO());

        assertEquals(200, response.getStatusCodeValue());
        verify(answerService, times(1)).updateAnswer(eq(1L), any(Answer.class));
    }

    @Test
    void testDeleteAnswer() {
        when(answerService.deleteAnswer(1L)).thenReturn(true);

        ResponseEntity<?> response = answerController.deleteAnswer(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(answerService, times(1)).deleteAnswer(1L);
    }
}
