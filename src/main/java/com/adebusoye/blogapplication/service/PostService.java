package com.adebusoye.blogapplication.service;

import com.adebusoye.blogapplication.dto.PostDto;

import java.util.List;
// One of the reason behind using this Service Interface is to encourage mapping
public interface PostService {
    List<PostDto>findAllPosts();  // Retrieve all posts from the database table
    List<PostDto> findPostByUser(); //107 Refactor List Posts Feature to List Only LoggedIn
    void createPost(PostDto postDto);
    PostDto findPostById(Long postId);
    void updatePost(PostDto postDto);
    void deletePost(Long postId);
    PostDto findPostByUrl(String postUrl); // View
    List<PostDto> searchPosts(String query);

}
