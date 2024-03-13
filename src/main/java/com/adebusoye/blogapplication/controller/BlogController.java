package com.adebusoye.blogapplication.controller;


import com.adebusoye.blogapplication.dto.CommentDto;
import com.adebusoye.blogapplication.dto.PostDto;
import com.adebusoye.blogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {
    PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    //68 handler method to handle view[List of Blog Posts-Client side]
    // http://localhost:8080/
    @GetMapping("/")
    public String viewBlogPosts(Model model){
        List<PostDto> postsResponse = postService.findAllPosts(); /**/
        model.addAttribute("postsResponse", postsResponse);
        return "blog/view_posts";

    }

    //70 handler method to handle show post request coming from "blog/view_posts"  //
    @GetMapping("/post/{postUrl}")  /* a new page development coming from viw_posts*/
    private String showPost(@PathVariable("postUrl") String postUrl,
                            Model model) {
        PostDto postDto = postService.findPostByUrl(postUrl);
        // Create comment form Handling
       CommentDto commentDto = new CommentDto();  //79 Create comment form Handling
        model.addAttribute("post", postDto); /*post displays new page*/
        model.addAttribute("comment",commentDto);  //79 Create comment form Handling
        return "blog/blog_post";
    }



    // handler method to handle blog post search request
    // http://localhost:8080/page/search?query=java
    @GetMapping("/page/search")
    public String searchPosts(@RequestParam(value = "query") String query,
                              Model model){
        List<PostDto> postsResponse = postService.searchPosts(query);
        model.addAttribute("postsResponse", postsResponse);
        return "blog/view_posts";
    }
}
