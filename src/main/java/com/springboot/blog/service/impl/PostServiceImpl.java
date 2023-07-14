package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postdto) {
        Post post = mapToPost(postdto);
        Post newpost = postRepository.save(post);
        PostDto responsepost = mapToDTO(newpost);
        return responsepost;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts =  postRepository.findAll();
        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePostById(PostDto postdto,long id) {
        //get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        post.setTitle(postdto.getTitle());
        post.setContent(postdto.getContent());
        post.setDescription(postdto.getDescription());
        Post updatedpost = postRepository.save(post);
        return mapToDTO(post);
    }

    @Override
    public void deletePostById(long id) {
        postRepository.deleteById(id);
        return;
    }

    //converted dto to entity
    private Post mapToPost(PostDto postdto){
        Post post = new Post();
        post.setTitle(postdto.getTitle());
        post.setDescription(postdto.getDescription());
        post.setContent(postdto.getContent());
        return post;
    }

    //converted entity to dto
    private PostDto mapToDTO(Post post){
        PostDto postdto = new PostDto();
        postdto.setId(post.getId());
        postdto.setContent(post.getContent());
        postdto.setDescription(post.getDescription());
        postdto.setTitle(post.getTitle());
        return postdto;
    }
}
