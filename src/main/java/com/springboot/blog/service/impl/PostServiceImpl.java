package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper mapper;
    private CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper,CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postdto) {
        Category category = categoryRepository.findById(postdto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category","Id", postdto.getCategoryId()));
        Post post = mapToEntity(postdto);
        post.setCategory(category);
        Post newpost = postRepository.save(post);
        PostDto responsePost = mapToDTO(newpost);
        return responsePost;
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
        Category category = categoryRepository.findById(postdto.getId()).orElseThrow(() -> new ResourceNotFoundException("Category","Id", postdto.getCategoryId()));
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        post.setTitle(postdto.getTitle());
        post.setContent(postdto.getContent());
        post.setDescription(postdto.getDescription());
        post.setCategory(category);
        Post updatedpost = postRepository.save(post);
        return mapToDTO(post);
    }

    @Override
    public void deletePostById(long id) {
        postRepository.deleteById(id);
        return;
    }

    @Override
    public List<PostDto> getAllPostByCategory(long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Id",categoryId));
        List<Post> posts = postRepository.findByCategoryId(categoryId);
        return posts.stream().map((post) -> mapToDTO(post)).collect(Collectors.toList());
    }

    //converted dto to entity
    private Post mapToEntity(PostDto postdto){
        Post post = mapper.map(postdto,Post.class);
        return post;
    }

    //converted entity to dto
    private PostDto mapToDTO(Post post){
        PostDto postdto = mapper.map(post, PostDto.class);
        return postdto;
    }
}
