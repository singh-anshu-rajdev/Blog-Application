package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.hibernate.annotations.Comments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentrepo;
    private PostRepository postrepo;

    public CommentServiceImpl(CommentRepository commentrepo, PostRepository postrepo){
        this.commentrepo = commentrepo;
        this.postrepo = postrepo;
    }

    @Override
    public CommentDto createComment(long postid, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);
        Post post = postrepo.findById(postid).orElseThrow(()-> new ResourceNotFoundException("Post","id",postid));
        comment.setPost(post);
        Comment newComment = commentrepo.save(comment);
        CommentDto commentdto = mapToDTO(newComment);
        return commentdto;
    }

    @Override
    public List<CommentDto> getAllCommentsbyPostId(long postId) {
        //retrieve comments by postid
        List<Comment> Comments = commentrepo.findByPostId(postId);

        //converting all the entities to dto
        return  Comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        // finding post by postId
        Post post = postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));

        //finding comment by comment Id
        Comment comment = commentrepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","id",commentId));

        //making the check
        if(comment.getPost().getId()!=post.getId()){
            throw new BlogAPIException(HttpStatus.NOT_FOUND,"Comment does not belong to the post");
        }
        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateById(long postId, long commentId, CommentDto commentdto) {

        Post post = postrepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post","id",postId));
        Comment comment = commentrepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment","id",commentId));

        if(comment.getPost().getId()!=(post.getId())) {
            throw new BlogAPIException(HttpStatus.NOT_FOUND,"comment does not belong to post");
        }

        comment.setName(commentdto.getName());
        comment.setEmail(commentdto.getEmail());
        comment.setBody(commentdto.getBody());

        Comment newComment = commentrepo.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public void deletebyId(long postId, long commentId) {

        //retrieve post by postId
        Post post = postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));

        //retrieve comment by commentId
        Comment comment = commentrepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment","id",commentId));

        commentrepo.deleteById(commentId);
    }


    // convert entity to dto
    private CommentDto mapToDTO(Comment comment){
        CommentDto commentdto = new CommentDto();
        commentdto.setId(comment.getId());
        commentdto.setName(comment.getName());
        commentdto.setBody(comment.getBody());
        commentdto.setEmail(comment.getEmail());
        return commentdto;
    }

    //convert dto to entity
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());;
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
