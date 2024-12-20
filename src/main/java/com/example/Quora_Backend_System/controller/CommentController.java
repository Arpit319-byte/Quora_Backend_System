package com.example.Quora_Backend_System.controller;

import com.example.Quora_Backend_System.DTO.CommentDTO;
import com.example.Quora_Backend_System.model.Comment;
import com.example.Quora_Backend_System.service.CommentService;
import com.example.Quora_Backend_System.service.UserService;
import com.example.Quora_Backend_System.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/comments")
@Tag(name = "Comments", description = "Operations related to comments")
public class CommentController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;
    private final UserService userService;
    private final AnswerService answerService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService, AnswerService answerService) {
        this.commentService = commentService;
        this.userService = userService;
        this.answerService = answerService;
    }

    @GetMapping
    @Operation(summary = "Get all comments", description = "Fetch a list of all comments")
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        logger.info("Getting all comments");
        List<CommentDTO> commentDTOs = commentService.getAllComments().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(commentDTOs);
    }

    @GetMapping("/{commentId}")
    @Operation(summary = "Get comment by id", description = "Fetch a comment by id")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long commentId) {
        Comment comment = commentService.getCommentById(commentId);
        if (comment == null) {
            logger.warn("Comment not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Getting comment by id - " + commentId);
        return ResponseEntity.ok(convertToDTO(comment));
    }

    @PostMapping
    @Operation(summary = "Create a comment", description = "Create a comment with given Response Body")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        logger.info("Creating comment");
        Comment comment = convertToEntity(commentDTO);
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.ok(convertToDTO(createdComment));
    }

    @PutMapping("/{commentId}")
    @Operation(summary = "Update a comment", description = "Update a comment by given id")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentDTO commentDTO) {
        Comment comment = convertToEntity(commentDTO);
        Comment updatedComment = commentService.updateComment(commentId, comment);
        if (updatedComment == null) {
            logger.warn("Comment not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Updating comment");
        return ResponseEntity.ok(convertToDTO(updatedComment));
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Delete a comment", description = "Delete a comment by given id")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        boolean isDeleted = commentService.deleteComment(commentId);
        if (!isDeleted) {
            logger.warn("Comment not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("Deleting comment");
        return ResponseEntity.noContent().build();
    }

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setUserId(comment.getUser().getId());
        commentDTO.setAnswerId(comment.getAnswer().getId());
        return commentDTO;
    }

    private Comment convertToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setContent(commentDTO.getContent());
        comment.setUser(userService.getUserById(commentDTO.getUserId()));
        comment.setAnswer(answerService.getAnswerById(commentDTO.getAnswerId()));
        return comment;
    }
}