package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService commentservice;

    public CommentController(CommentService commentservice){
        this.commentservice = commentservice;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long id, @RequestBody CommentDto commentdto){
        return new ResponseEntity<>(commentservice.createComment(id,commentdto) , HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable(value = "postId") long postId){
        return ResponseEntity.ok(commentservice.getAllCommentsbyPostId(postId));
    }

    @GetMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> getbyId(@PathVariable(value = "postId") long postId,@PathVariable(value= "commentId") long CommentId){
        System.out.println("hey");
        return ResponseEntity.ok(commentservice.getCommentById(postId, CommentId));
    }

    @PutMapping("/{postId}/{commentId}/update")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId, @RequestBody CommentDto commentdto){
        return ResponseEntity.ok(commentservice.updateById(postId, commentId, commentdto));
    }

    @DeleteMapping("/{postId}/{commentId}/delete")
    public ResponseEntity<String> deletebyId(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId){
        commentservice.deletebyId(postId, commentId);
        return new ResponseEntity<>("Comment has been deleted Successfully",HttpStatus.OK);
    }
}
