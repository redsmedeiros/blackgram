package com.blackGram.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blackGram.entity.Comment;
import com.blackGram.entity.Post;
import com.blackGram.exception.BlackGramAPIException;
import com.blackGram.exception.ResourceNotFoundException;
import com.blackGram.payload.CommentDto;
import com.blackGram.repository.CommentRepository;
import com.blackGram.repository.PostRepository;
import com.blackGram.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;

    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository){
        
        this.postRepository = postRepository;

        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("postId", "postId", postId));

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);

        List<CommentDto> commentsResponse = comments.stream().map( comment -> mapToDto(comment)).collect(Collectors.toList());
        
        return commentsResponse;
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("postId", "postId", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("commentId", "commentId", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlackGramAPIException(HttpStatus.BAD_REQUEST, "Comentário não pertence ao post");
        }
       
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentByid(long postId, long commentId, CommentDto commentDto) {
        
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("postId", "postId", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("cntId", "commentId", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlackGramAPIException(HttpStatus.BAD_REQUEST, "Comentário não pertence ao post");
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updateComment = commentRepository.save(comment);

        return mapToDto(updateComment);
    }

    @Override
    public void deteteComment(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("postId", "postId", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("commentId", "commentId", commentId));

        if(comment.getPost().getId().equals(post.getId())){
            throw new BlackGramAPIException(HttpStatus.BAD_REQUEST, "Comentário não pertence ao post");
        }

        commentRepository.delete(comment);
    }


    private CommentDto mapToDto(Comment comment){

        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;

    }

    private Comment mapToEntity(CommentDto commentDto){

        Comment comment = new Comment();

        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        return comment;

    }

   
   

  

    
    
}
