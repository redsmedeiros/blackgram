package com.blackGram.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blackGram.entity.Post;
import com.blackGram.payload.PostDto;
import com.blackGram.repository.PostRepository;
import com.blackGram.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);
       
        return mapToDto(newPost);
    }

    @Override
    public List<PostDto> getAllPosts() {

        List<Post> posts = postRepository.findAll();

        List<PostDto> postsResponse = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
       
        return postsResponse;
    }

    private Post mapToEntity(PostDto postDto){

        Post post = new Post();

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;

    }

    private PostDto mapToDto(Post post){

        PostDto postDto = new PostDto();

        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());

        return postDto;

    }

   
    
}
