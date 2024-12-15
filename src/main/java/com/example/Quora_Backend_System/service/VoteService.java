package com.example.Quora_Backend_System.service;

import com.example.Quora_Backend_System.model.Vote;
import com.example.Quora_Backend_System.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    private static final Logger logger = LoggerFactory.getLogger(VoteService.class);
    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> getAllVotes() {
        logger.info("Getting all votes");
        return voteRepository.findAll();
    }

    public Vote getVoteById(Long voteId) {
        logger.info("Getting vote by id - " + voteId);
        Vote vote = voteRepository.findById(voteId).orElse(null);
        if (vote == null) {
            logger.warn("Vote not found");
            return null;
        }
        return vote;
    }

    public Vote createVote(Vote vote) {
        logger.info("Creating vote");

        try {
            return voteRepository.save(vote);
        } catch (Exception e) {
            logger.error("Error creating vote", e);
            return null;
        }
    }

    public Vote updateVote(Long voteId, Vote vote) {
        logger.info("Updating vote by id - " + voteId);
        Vote updatedVote = voteRepository.findById(voteId).orElse(null);
        if (updatedVote == null) {
            logger.warn("Vote not found");
            return null;
        }
        return voteRepository.save(updatedVote);
    }

    public void deleteVote(Long voteId) {
        logger.info("Deleting vote by id - " + voteId);
        Vote vote = voteRepository.findById(voteId).orElse(null);
        if (vote == null) {
            logger.warn("Vote not found");
            return;
        }
        voteRepository.delete(vote);
    }
}
