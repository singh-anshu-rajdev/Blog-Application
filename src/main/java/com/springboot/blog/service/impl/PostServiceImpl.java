package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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
    public PostResponse getAllPosts(int pageno, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //create pageable instance
        PageRequest pageable = PageRequest.of(pageno,pageSize,sort);
        Page<Post> posts =  postRepository.findAll(pageable);

        //get content for page object

        List<Post> listposts = posts.getContent();


        List<PostDto> content =  listposts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
        PostResponse postresponse = new PostResponse();
        postresponse.setContent(content);
        postresponse.setPageSize(posts.getNumber());
        postresponse.setTotalElements(posts.getTotalElements());
        postresponse.setTotalPages(posts.getTotalPages());
        postresponse.setLast(posts.isLast());

        return postresponse;
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
