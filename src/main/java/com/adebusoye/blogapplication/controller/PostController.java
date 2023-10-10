package com.adebusoye.blogapplication.controller;

import com.adebusoye.blogapplication.dto.PostDto;
import com.adebusoye.blogapplication.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    // handler method to creat Post
    @GetMapping("/admin/posts")  // http://localhost:8080/admin/posts
    public String posts(Model model) {
        List<PostDto> posts = postService.findAllPosts(); //  To allow CRUD operations
        model.addAttribute("posts", posts);
        return "/admin/posts"; // Thymeleaf view name

    }
    // handler method to handle NewPost request
    @GetMapping("/admin/posts/newpost")
    //  build tposts and newtpost in tposts.html/header in nav <li , <a @{admin/tposts /a> T-Post
    // handler method to handle new post request
    public String newPost(Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);   ///
        return "admin/create_post"; // Create Post Form Handling in template.admin in Html and design
    }
    // handler method to handle create form submit request
    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post")  PostDto postDto, //Step3: Valid PostDto data

                                 BindingResult result,   // step4:Use BindingResult to Check Errors and Return to UI
                                 Model model          // step5 @ModelAttribute(""post) will pass comment for empty submission
    ){ // ModelAttribute annotation will read form data and set the values to fields of the model object
        if(result.hasErrors()){
            model.addAttribute("post", postDto);
            return "admin/create_post";  //
        }

        postDto.setUrl(getUrl(postDto.getTitle()));  // link Url must be set
        postService.createPost(postDto);
        return "redirect:/admin/posts";  // redirecting to url link at Top
    }

    //handler method to handle edit post request
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId,
                               Model model){

        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post", postDto);
        return "admin/edit_post";

    }

    // handler method to handle edit post form submit request - redirect
    @PostMapping("/admin/posts/{postId}")
    public  String  updatePost(@PathVariable("postId") Long postId,
                               @Valid @ModelAttribute("post") PostDto postDto,
                               BindingResult resul,
                               Model model) {
        if(resul.hasErrors()){
            model.addAttribute("post",postDto);
            return  "admin/edit_post";
        }
        postDto.setId(postId);
        postService.updatePost(postDto);
        return "redirect:/admin/posts";
    }

    // handler method to handle delete post request
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return "redirect:/admin/posts";

    }
    // 60 handle method to handle view post request
    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl,
                           Model model) {
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post", postDto);
        return "admin/view_post";
    }

    //Handler method to handle search blog posts request
    // localhost:8080/admin/posts/search?query=java
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query,
                              Model model){
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "admin/posts";


    }

    // Url
    private static String getUrl(String postTitle) {
        // OOPS Concepts Explained in Java
        // oops-concepts-explained-in-java
        String title = postTitle.trim().toLowerCase();
        String url= title.replaceAll("\\s+","-");
        url = url.replaceAll("[A-Za-z0-9]","-");
        return url;

    }
}
