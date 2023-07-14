package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
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
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto){
        return new ResponseEntity<>(postservice.createPost(postdto), HttpStatus.CREATED);
    }

    @GetMapping("/getposts")
    public List<PostDto> getAllPost(){
        return postservice.getAllPosts();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable("id") long id){
        return ResponseEntity.ok(postservice.getPostById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postdto , @PathVariable("id") long id){
        return ResponseEntity.ok(postservice.updatePostById(postdto,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id){
        postservice.deletePostById(id);
        return ResponseEntity.ok("Post has been deleted Successfully");
    }

}
