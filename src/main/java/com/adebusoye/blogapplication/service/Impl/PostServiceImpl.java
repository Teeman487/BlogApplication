package com.adebusoye.blogapplication.service.Impl;

import com.adebusoye.blogapplication.dto.PostDto;
import com.adebusoye.blogapplication.entity.Post;
import com.adebusoye.blogapplication.mapper.PostMapper;
import com.adebusoye.blogapplication.repository.PostRepository;
import com.adebusoye.blogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
   @Autowired
    PostRepository postRepository;
    @Override
    public List<PostDto> findAllPosts() { // PostDto serves as Model
        List<Post>posts=postRepository.findAll(); //FETCHING DATA FROM ENTITY-DATABASE
        return posts.stream().map( PostMapper::mapToPostDto) // Post Entity mapping PostDto
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {     //createPost will redirect to Repository data-Post-Entity
        Post post = PostMapper.mapToPost(postDto);  // map PostDto to  Post entity
        postRepository.save(post); // createPost saved in Post Entity
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.mapToPostDto(post); // entity map to Dto
    }

    @Override
    public void updatePost(PostDto postDto) {
          Post post = PostMapper.mapToPost(postDto);//Dto to Entity
          postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts =postRepository.searchPosts(query);
        // convert list of posts entity to list of postDtos using stream
        return  posts.stream()
                .map(PostMapper::mapToPostDto).
                collect(Collectors.toList());

    }
}
