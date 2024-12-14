package com.example.Quora_Backend_System.service;

import com.example.Quora_Backend_System.model.Question;
import com.example.Quora_Backend_System.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuestionService {

    private final static Logger logger = LoggerFactory.getLogger(QuestionService.class);

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public List<Question> getAllQuestions() {
        logger.info("Getting all questions from the database");
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long questionId) {
        logger.info("Getting question by id - {} from the database", questionId);

        try {
            Question question = questionRepository.findById(questionId).orElse(null);
            if (question == null) {
                logger.error("Question not found for id - {}", questionId);
                return null;
            }
            return question;
        } catch (Exception e) {
            logger.error("Error occurred while getting question by id - {}", questionId, e);
            return null;
        }
    }

    public Question createQuestion(Question question) {
        logger.info("Creating question in the database");

        try {
            return questionRepository.save(question);
        } catch (Exception e) {
            logger.error("Error occurred while creating question in the database", e);
            return null;
        }
    }

    public Question updateQuestion(Long questionId, Question question) {
        logger.info("Updating question in the database");

        try {
            Question existingQuestion = questionRepository.findById(questionId).orElse(null);
            if (existingQuestion == null) {
                logger.error("Question not found for id - {}", questionId);
                return null;
            }
            existingQuestion.setContent(question.getContent());
            return questionRepository.save(existingQuestion);
        } catch (Exception e) {
            logger.error("Error occurred while updating question in the database", e);
            return null;
        }
    }

    public Boolean deleteQuestion(Long questionId) {

        logger.info("Deleting question from the database with id- {}", questionId);

        try {
            Question question = questionRepository.findById(questionId).orElse(null);
            if (question == null) {
                logger.error("Question not found for id - {}", questionId);
                return false;
            }
            questionRepository.deleteById(questionId);
            return true;
        } catch (Exception e) {
            logger.error("Error occurred while deleting question from the database with id- {}", questionId, e);
            return false;
        }
    }
}
