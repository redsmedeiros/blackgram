package com.blackGram.service;

import java.util.List;

import com.blackGram.payload.PostDto;
import com.blackGram.payload.PostResponse;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long postId);

    PostDto updatePost(long postId, PostDto postDto);

    void deletePost(long postId);
    
}
