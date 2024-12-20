package com.example.Quora_Backend_System.controller;

import com.example.Quora_Backend_System.DTO.VoteDTO;
import com.example.Quora_Backend_System.model.Answer;
import com.example.Quora_Backend_System.model.Vote;
import com.example.Quora_Backend_System.service.AnswerService;
import com.example.Quora_Backend_System.service.UserService;
import com.example.Quora_Backend_System.service.VoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/votes")
@Tag(name = "Votes", description = "Operations related to vote")
public class VoteController {

    private static final Logger logger = LoggerFactory.getLogger(VoteController.class);

    private final VoteService voteService;
    private final UserService userService;
    private final AnswerService answerService;


    public VoteController(VoteService voteService, UserService userService, AnswerService answerService) {
        this.voteService = voteService;
        this.userService = userService;
        this.answerService = answerService;
    }

    @GetMapping
    public ResponseEntity<List<VoteDTO>> getAllVotes() {
        logger.info("Getting all votes");
        List<VoteDTO> voteDTOs = voteService.getAllVotes().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(voteDTOs);
    }

    @GetMapping("/{voteId}")
    public ResponseEntity<VoteDTO> getVoteById(@PathVariable Long voteId) {
        logger.info("Getting vote by id - " + voteId);
        Vote vote = voteService.getVoteById(voteId);
        if (vote == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDTO(vote));
    }

    @PostMapping
    public ResponseEntity<VoteDTO> createVote(@Valid @RequestBody VoteDTO voteDTO) {
        logger.info("Creating vote");
        Vote vote = convertToEntity(voteDTO);
        Vote createdVote = voteService.createVote(vote);
        return ResponseEntity.ok(convertToDTO(createdVote));
    }

    @PutMapping("/{voteId}")
    public ResponseEntity<VoteDTO> updateVote(@PathVariable Long voteId, @Valid @RequestBody VoteDTO voteDTO) {
        logger.info("Updating vote by id - " + voteId);
        Vote vote = convertToEntity(voteDTO);
        Vote updatedVote = voteService.updateVote(voteId, vote);
        if (updatedVote == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDTO(updatedVote));
    }

    @DeleteMapping("/{voteId}")
    public ResponseEntity<Void> deleteVote(@PathVariable Long voteId) {
        logger.info("Deleting vote by id - " + voteId);
        voteService.deleteVote(voteId);
        return ResponseEntity.noContent().build();
    }

    private VoteDTO convertToDTO(Vote vote) {
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setId(vote.getId());
        voteDTO.setUserId(vote.getUser().getId());
        voteDTO.setAnswerId(vote.getAnswer().getId());
        voteDTO.setVoteType(vote.getVoteType());
        return voteDTO;
    }

    private Vote convertToEntity(VoteDTO voteDTO) {
        Vote vote = new Vote();
        vote.setId(voteDTO.getId());
        vote.setUser(userService.getUserById(voteDTO.getUserId()));
        vote.setAnswer(answerService.getAnswerById(voteDTO.getAnswerId()));
        vote.setVoteType(voteDTO.getVoteType());
        return vote;
    }
}