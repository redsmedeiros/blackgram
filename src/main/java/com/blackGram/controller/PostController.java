package com.blackGram.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackGram.payload.PostDto;
import com.blackGram.service.PostService;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){

        PostDto postResponse = postService.createPost(postDto);

        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);

    }

    @GetMapping
    public List<PostDto> getAllPosts(){

        List<PostDto> postResponse = postService.getAllPosts();

        return postResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long postId){

        PostDto postResponse = postService.getPostById(postId);

        return new ResponseEntity<PostDto>(postResponse, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name = "id") long postId, @RequestBody PostDto postDto){

        PostDto postResponse = postService.updatePost(postId, postDto);

        return new ResponseEntity<PostDto>(postResponse, HttpStatus.OK);
    }
    
}
