package com.example.Quora_Backend_System.service;

import com.example.Quora_Backend_System.model.Vote;
import com.example.Quora_Backend_System.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        logger.info("Getting all votes from the database");
        try {
            return voteRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while getting all votes from the database", e);
            return null;
        }
    }

    public Vote getVoteById(Long voteId) {
        logger.info("Getting vote by id - {}", voteId);
        try {
            return voteRepository.findById(voteId).orElse(null);
        } catch (Exception e) {
            logger.error("Error occurred while getting vote by id - " + voteId, e);
            return null;
        }
    }

    @Transactional
    public Vote createVote(Vote vote) {
        logger.info("Creating vote in the database");
        try {
            return voteRepository.save(vote);
        } catch (Exception e) {
            logger.error("Error occurred while creating vote in the database", e);
            return null;
        }
    }

    @Transactional
    public Vote updateVote(Long voteId, Vote vote) {
        logger.info("Updating vote by id - {}", voteId);
        try {
            Vote voteToUpdate = voteRepository.findById(voteId).orElse(null);
            if (voteToUpdate == null) {
                logger.error("Vote not found with id {}", voteId);
                return null;
            }
            voteToUpdate.setVoteType(vote.getVoteType());
            return voteRepository.save(voteToUpdate);
        } catch (Exception e) {
            logger.error("Error occurred while updating vote by id - " + voteId, e);
            return null;
        }
    }

    @Transactional
    public Boolean deleteVote(Long voteId) {
        logger.info("Deleting vote by id - {}", voteId);
        try {
            Vote voteToDelete = voteRepository.findById(voteId).orElse(null);
            if (voteToDelete == null) {
                logger.error("Vote not found with id {}", voteId);
                return false;
            }
            voteRepository.delete(voteToDelete);
            return true;
        } catch (Exception e) {
            logger.error("Error occurred while deleting vote by id - " + voteId, e);
            return false;
        }
    }
}