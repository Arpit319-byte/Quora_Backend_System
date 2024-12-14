package com.example.Quora_Backend_System.service;

import com.example.Quora_Backend_System.model.Comment;
import com.example.Quora_Backend_System.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final static Logger logger = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments() {
        logger.info("Getting all comments from the database");
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long commentId) {
        logger.info("Getting comment by id - {} from the database", commentId);
        try {
            Comment comment = commentRepository.findById(commentId).orElse(null);
            if (comment == null) {
                logger.error("Comment not found for id - {}", commentId);
                return null;
            }
            return comment;
        }catch(Exception e) {
            logger.error("Error occurred while getting comment by id - {}", commentId, e);
            return null;
        }
    }

    public Comment createComment(Comment comment) {

        logger.info("Creating comment in the database");
        try {
            return commentRepository.save(comment);
        }catch(Exception e) {
            logger.error("Error occurred while creating comment in the database", e);
            return null;
        }

    }

    public Comment updateComment(Long commentId, Comment comment) {

        logger.info("Updating comment in the database");
        try {
            Comment existingComment = commentRepository.findById(commentId).orElse(null);
            if (existingComment == null) {
                logger.error("Comment not found for id - {}", commentId);
                return null;
            }
            existingComment.setContent(comment.getContent());
            existingComment.setQuestion(comment.getQuestion());
            existingComment.setUser(comment.getUser());
            existingComment.setAnswer(comment.getAnswer());
            existingComment.setComment(comment.getComment());
            return commentRepository.save(comment);
        }catch (Exception e) {
            logger.error("Error occurred while updating comment in the database", e);
            return null;
        }
    }

    public boolean deleteComment(Long commentId) {

        return false;
    }
}
