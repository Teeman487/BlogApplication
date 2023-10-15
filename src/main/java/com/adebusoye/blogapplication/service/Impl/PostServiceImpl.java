package com.adebusoye.blogapplication.service.Impl;

import com.adebusoye.blogapplication.dto.PostDto;
import com.adebusoye.blogapplication.entity.Post;
import com.adebusoye.blogapplication.entity.User;
import com.adebusoye.blogapplication.mapper.PostMapper;
import com.adebusoye.blogapplication.repository.PostRepository;
import com.adebusoye.blogapplication.repository.UserRepository;
import com.adebusoye.blogapplication.service.PostService;
import com.adebusoye.blogapplication.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
   @Autowired
    PostRepository postRepository;
   @Autowired
    UserRepository userRepository;
    @Override
    public List<PostDto> findAllPosts() { // PostDto serves as Model
        List<Post>posts=postRepository.findAll(); //FETCHING DATA FROM ENTITY-DATABASE
        return posts.stream().map( PostMapper::mapToPostDto) // Post Entity mapping PostDto
                .collect(Collectors.toList());
    }

    //find list OF POSTS of  all loggedIn User from database // logic to get list of BlogPosts from loggedIn users
    @Override
    public List<PostDto> findPostByUser() { // logic to get list of BlogPosts from loggedIn users
        String email = SecurityUtils.getCurrentUser().getUsername(); // Current Logged In User from database
        User createdBy = userRepository.findByEmail(email); // Repository located Current Logged In User from database

        Long userId = createdBy.getId(); // Current Log In User from database located all users by id[]
        List<Post> posts = postRepository.findPostsByUser(userId); // repo finds  Current Log In User from database to locate all users by id[]
        return posts.stream()
                .map( PostMapper::mapToPostDto)
                .collect(Collectors.toList());

    }
    /* @Override
    public void createPost(PostDto postDto) {     //createPost will redirect to Repository data-Post-Entity
        String email = SecurityUtils.getCurrentUser().getUsername();   // store the current logged-in userID in a posts table // we got login user email ID
        User user = userRepository.findByEmail(email); // GET User object by email
        Post post = PostMapper.mapToPost(postDto);  // map PostDto to  Post entity
        post.setCreatedBy(user);
        postRepository.save(post); // createPost saved in Post Entity
    }*/
    /* @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts =postRepository.searchPosts(query);
        // convert list of posts entity to list of postDtos using stream
        return  posts.stream()
                .map(PostMapper::mapToPostDto).
                collect(Collectors.toList());

    }*/

    // A logg in user can now do multiple posts
    @Override
    public void createPost(PostDto postDto) {     //createPost will redirect to Repository data-Post-Entity
        String email = SecurityUtils.getCurrentUser().getUsername();   // store the current logged-in userID in a posts table // we got login user email ID
        User user = userRepository.findByEmail(email); // GET User object by email or String.id
        Post post = PostMapper.mapToPost(postDto);  // map PostDto to  Post entity
        post.setCreatedBy(user); // a logged in user can now do multiple posts
        postRepository.save(post); // createPost saved in Post Entity
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.mapToPostDto(post); // entity map to Dto
    }

    @Override
    public void updatePost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername(); //
        User createdBy = userRepository.findByEmail(email); //
          Post post = PostMapper.mapToPost(postDto);//Dto to Entity
        post.setCreatedBy(createdBy);
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
