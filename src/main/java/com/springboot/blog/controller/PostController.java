package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstraints;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postservice;

    public PostController(PostService postservice) {
        this.postservice = postservice;
    }

    @PostMapping("/createPost")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postdto){
        return new ResponseEntity<>(postservice.createPost(postdto), HttpStatus.CREATED);
    }

    @GetMapping("/getposts")
    public PostResponse getAllPost(
            @RequestParam(value = "pageNo", defaultValue = AppConstraints.DEFAULT_PAGE_NUMBER, required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstraints.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstraints.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstraints.DEFAULT_SORT_DIRECTION,required = false) String sortDir){
        return postservice.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable("id") long id){
        return ResponseEntity.ok(postservice.getPostById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postdto , @PathVariable("id") long id){
        return ResponseEntity.ok(postservice.updatePostById(postdto,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id){
        postservice.deletePostById(id);
        return ResponseEntity.ok("Post has been deleted Successfully");
    }

}
