package com.example.Quora_Backend_System.service;

import com.example.Quora_Backend_System.model.Answer;
import com.example.Quora_Backend_System.repository.AnswerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswerService {

    private final static Logger logger = LoggerFactory.getLogger(AnswerService.class);

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }


    public List<Answer> getAllAnswers() {
        logger.info("Getting all answers from the database");

        try {
            List<Answer> answers = answerRepository.findAll();
            return answers;
        } catch (Exception e) {
            logger.error("Error occurred while getting all answers from the database", e);
            return null;
        }
    }

    public Answer getAnswerById(Long answerId) {

        logger.info("Getting answer by id - {} from the database",answerId);

        try {
            return answerRepository.findById(answerId).orElse(null);
        } catch (Exception e) {
            logger.error("Error occurred while getting answer by id - " + answerId, e);
            return null;
        }
    }

    @Transactional
    public Answer createAnswer(Answer answer) {

        logger.info("Creating answer in the database");

        try {
            return answerRepository.save(answer);
        } catch (Exception e) {
            logger.error("Error occurred while creating answer in the database", e);
            return null;
        }
    }

    @Transactional
    public Answer updateAnswer(Long answerId, Answer answer) {

        logger.info("Updating answer by id - {} in the database", answerId);

        try {
            Answer answerToUpdate = answerRepository.findById(answerId).orElse(null);
            if (answerToUpdate == null) {
                logger.error("Answer not found with id {} in the database",answerId);
                return null;
            }
            answerToUpdate.setContent(answer.getContent());
            return answerRepository.save(answerToUpdate);
        } catch (Exception e) {
            logger.error("Error occurred while updating answer by id - " + answerId, e);
            return null;
        }
    }

    @Transactional
    public Boolean deleteAnswer(Long answerId) {

            logger.info("Deleting answer by id - {} from the database", answerId);

            try {
                Answer answerToDelete = answerRepository.findById(answerId).orElse(null);
                if (answerToDelete == null) {
                    logger.error("Answer not found with id {} in the database",answerId);
                    return false;
                }
                answerRepository.delete(answerToDelete);
                return true;
            } catch (Exception e) {
                logger.error("Error occurred while deleting answer by id - " + answerId, e);
                return false;
            }
    }
}
