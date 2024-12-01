package com.example.Quora_Backend_System.controller;

import com.example.Quora_Backend_System.model.Comment;
import com.example.Quora_Backend_System.service.CommentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments(){
        logger.info("Getting all comments");
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long commentId){
        Comment comment = commentService.getCommentById(commentId);
        if(comment == null){
            logger.warn("Comment not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Getting comment by id - " + commentId);
        return ResponseEntity.ok(comment);
    }
    
    @PostMapping
    public ResponseEntity<Comment> createComment(@Valid @RequestBody Comment comment){
        logger.info("Creating comment");
        return ResponseEntity.ok(commentService.createComment(comment));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @Valid @RequestBody Comment comment){
        Comment updatedComment = commentService.updateComment(commentId, comment);
        if(updatedComment == null){
            logger.warn("Comment not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Updating comment");
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){
        if(commentService.deleteComment(commentId)){
            logger.info("Deleting comment");
            return ResponseEntity.ok().build();
        }
        logger.warn("Comment not found");
        return ResponseEntity.notFound().build();
    }

}
