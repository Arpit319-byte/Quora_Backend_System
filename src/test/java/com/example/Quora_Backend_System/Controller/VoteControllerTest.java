package com.example.Quora_Backend_System.Controller;

import com.example.Quora_Backend_System.DTO.VoteDTO;
import com.example.Quora_Backend_System.controller.VoteController;
import com.example.Quora_Backend_System.model.Vote;
import com.example.Quora_Backend_System.service.VoteService;
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

class VoteControllerTest {

    @Mock
    private VoteService voteService;

    @InjectMocks
    private VoteController voteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllVotes() {
        Vote vote = new Vote();
        when(voteService.getAllVotes()).thenReturn(Collections.singletonList(vote));

        ResponseEntity<List<VoteDTO>> response = voteController.getAllVotes();

        assertEquals(200, response.getStatusCodeValue());
        verify(voteService, times(1)).getAllVotes();
    }

    @Test
    void testGetVoteById() {
        Vote vote = new Vote();
        when(voteService.getVoteById(1L)).thenReturn(vote);

        ResponseEntity<VoteDTO> response = voteController.getVoteById(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(voteService, times(1)).getVoteById(1L);
    }

    @Test
    void testCreateVote() {
        Vote vote = new Vote();
        when(voteService.createVote(any(Vote.class))).thenReturn(vote);

        ResponseEntity<VoteDTO> response = voteController.createVote(new VoteDTO());

        assertEquals(200, response.getStatusCodeValue());
        verify(voteService, times(1)).createVote(any(Vote.class));
    }

    @Test
    void testUpdateVote() {
        Vote vote = new Vote();
        when(voteService.updateVote(eq(1L), any(Vote.class))).thenReturn(vote);

        ResponseEntity<VoteDTO> response = voteController.updateVote(1L, new VoteDTO());

        assertEquals(200, response.getStatusCodeValue());
        verify(voteService, times(1)).updateVote(eq(1L), any(Vote.class));
    }

    @Test
    void testDeleteVote() {
        doNothing().when(voteService).deleteVote(1L);

        ResponseEntity<Void> response = voteController.deleteVote(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(voteService, times(1)).deleteVote(1L);
    }
}
