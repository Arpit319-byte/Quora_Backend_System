package com.example.Quora_Backend_System.service;

import com.example.Quora_Backend_System.model.Question;
import com.example.Quora_Backend_System.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        logger.info("Getting all questions from the database");
        try {
            return questionRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while getting all questions from the database", e);
            return null;
        }
    }

    public Question getQuestionById(Long questionId) {
        logger.info("Getting question by id - {}", questionId);
        try {
            return questionRepository.findById(questionId).orElse(null);
        } catch (Exception e) {
            logger.error("Error occurred while getting question by id - {}", questionId, e);
            return null;
        }
    }

    @Transactional
    public Question createQuestion(Question question) {
        logger.info("Creating question in the database");
        try {
            return questionRepository.save(question);
        } catch (Exception e) {
            logger.error("Error occurred while creating question in the database", e);
            return null;
        }
    }

    @Transactional
    public Question updateQuestion(Long questionId, Question question) {
        logger.info("Updating question by id - {}", questionId);
        try {
            Question existingQuestion = questionRepository.findById(questionId).orElse(null);
            if (existingQuestion == null) {
                logger.error("Question not found with id {}", questionId);
                return null;
            }
            existingQuestion.setContent(question.getContent());
            return questionRepository.save(existingQuestion);
        } catch (Exception e) {
            logger.error("Error occurred while updating question by id - {}", questionId, e);
            return null;
        }
    }

    @Transactional
    public Boolean deleteQuestion(Long questionId) {
        logger.info("Deleting question by id - {}", questionId);
        try {
            Question question = questionRepository.findById(questionId).orElse(null);
            if (question == null) {
                logger.error("Question not found with id {}", questionId);
                return false;
            }
            questionRepository.deleteById(questionId);
            return true;
        } catch (Exception e) {
            logger.error("Error occurred while deleting question by id - {}", questionId, e);
            return false;
        }
    }
}