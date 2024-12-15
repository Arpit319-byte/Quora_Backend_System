package com.example.Quora_Backend_System.controller;

import com.example.Quora_Backend_System.model.Vote;
import com.example.Quora_Backend_System.service.VoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/votes")
@Tag(name = "Votes", description = "Operations related to vote")
public class VoteController {

    private static final Logger logger = LoggerFactory.getLogger(VoteController.class);
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping
    public ResponseEntity<List<Vote>> getAllVotes() {
        logger.info("Getting all votes");
        return ResponseEntity.ok(voteService.getAllVotes());
    }

    @GetMapping("/{voteId}")
    public ResponseEntity<Vote> getVoteById(@PathVariable Long voteId) {
        logger.info("Getting vote by id - " + voteId);
        Vote vote = voteService.getVoteById(voteId);
        if (vote == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vote);
    }

    @PostMapping
    public ResponseEntity<Vote> createVote(@Valid @RequestBody Vote vote) {
        logger.info("Creating vote");
        return ResponseEntity.ok(voteService.createVote(vote));
    }

    @PutMapping("/{voteId}")
    public ResponseEntity<Vote> updateVote(@PathVariable Long voteId, @Valid @RequestBody Vote vote) {
        logger.info("Updating vote by id - " + voteId);
        Vote updatedVote = voteService.updateVote(voteId, vote);
        if (updatedVote == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedVote);
    }

    @DeleteMapping("/{voteId}")
    public ResponseEntity<Void> deleteVote(@PathVariable Long voteId) {
        logger.info("Deleting vote by id - " + voteId);
        voteService.deleteVote(voteId);
        return ResponseEntity.noContent().build();
    }
}
