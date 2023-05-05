package com.blackGram.service;

import java.util.List;

import com.blackGram.payload.CommentDto;

public interface CommentService {

    //comentário está vinculado a uma postagem
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    CommentDto updateCommentByid(long postId, long commentId, CommentDto commentDto);

    void deteteComment(long postId, long commentId);
    
}
