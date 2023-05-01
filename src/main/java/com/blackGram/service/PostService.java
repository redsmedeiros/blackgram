package com.blackGram.service;

import java.util.List;

import com.blackGram.payload.PostDto;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long postId);

    PostDto updatePost(long postId, PostDto postDto);
    
}
