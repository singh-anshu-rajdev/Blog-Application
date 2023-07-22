package com.springboot.blog.service;

import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost( PostDto postdto);

    PostResponse getAllPosts(int pageno, int pageSize, String sortBy,String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePostById(PostDto postdto , long id);

    void deletePostById(long id);

    List<PostDto> getAllPostByCategory(long categoryId);

}
