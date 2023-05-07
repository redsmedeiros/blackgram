package com.blackGram.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blackGram.payload.PostDto;
import com.blackGram.payload.PostResponse;
import com.blackGram.service.PostService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){

        PostDto postResponse = postService.createPost(postDto);

        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);

    }

    @GetMapping
    public PostResponse getAllPosts(
        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){

        PostResponse postResponse = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);

        return postResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long postId){

        PostDto postResponse = postService.getPostById(postId);

        return new ResponseEntity<PostDto>(postResponse, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @PathVariable(name = "id") long postId, @RequestBody PostDto postDto){

        PostDto postResponse = postService.updatePost(postId, postDto);

        return new ResponseEntity<PostDto>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long postId){

        postService.deletePost(postId);

        return new ResponseEntity<String>("Post deleted successfully", HttpStatus.OK);
    }
    
}
