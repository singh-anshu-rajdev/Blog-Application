package com.springboot.blog.service;

import com.springboot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long id, CommentDto commentDto);

    List<CommentDto> getAllCommentsbyPostId(long Postid);

    CommentDto getCommentById(long postId,long commentId);

    CommentDto updateById(long postId, long commentId, CommentDto commentdto);

    void deletebyId(long postId,long commentId);
}
