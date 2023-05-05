package com.blackGram.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackGram.payload.CommentDto;
import com.blackGram.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService){

        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId, @RequestBody CommentDto commentDto){

        CommentDto commentResponse = commentService.createComment(postId, commentDto);

        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentByPostid(@PathVariable(value = "postId") long postId){

        List<CommentDto> response = commentService.getCommentByPostId(postId);

        return response;
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId){

        CommentDto response = commentService.getCommentById(postId, commentId);

        return new ResponseEntity<CommentDto>(response, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId, @RequestBody CommentDto commentDto){

        CommentDto updatedComment = commentService.updateCommentByid(postId, commentId, commentDto);

        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId){

        commentService.deteteComment(postId, commentId);

        return new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
    }
    
}
