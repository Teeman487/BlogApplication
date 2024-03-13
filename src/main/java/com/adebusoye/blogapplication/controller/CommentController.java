package com.adebusoye.blogapplication.controller;

import com.adebusoye.blogapplication.dto.CommentDto;
import com.adebusoye.blogapplication.dto.PostDto;
import com.adebusoye.blogapplication.service.CommentService;
import com.adebusoye.blogapplication.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    CommentService commentService;
    PostService postService;
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    /* th:action="@{/{postUrl}/comments(postUrl=${post.url})}"
                                    th:object="${comment}"
                            >  <!--redirecting comment to localhost:8080/{postUrl}/comments-->*/
    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable("postUrl") String postUrl,
                                 @Valid @ModelAttribute("comment") CommentDto commentDto,
                                BindingResult result, // 81
                                Model model){ //@Valid //81

        // Handling post in comment
        PostDto postDto = postService.findPostByUrl(postUrl); // ERROR VALIDATION
        if(result.hasErrors()){
          model.addAttribute("post", postDto); // ERROR VALIDATION
            model.addAttribute("comment", commentDto);
            return "blog/blog_post";
        }
        commentService.createComment(postUrl, commentDto);  // saving comment to locate post per url(id),
        // hence we pass a special case @{/{postUrl}/comments(postUrl=${post.url})}"
        // where uri is: /{postUrl}/comments   &    (postUrl=${post.url}) = locates post on url
        return "redirect:/post/"+postUrl;     //@GetMapping("/post/{postUrl}") return "blog/blog_post";

        //return "redirect:/admin/posts";
    }

    /*@PostMapping("/admin/posts/{postId}")
    public  String  updatePost(@PathVariable("postId") Long postId,
                               @Valid @ModelAttribute("post") PostDto postDto,
                               BindingResult result,
                               Model model) {
        if(result.hasErrors()){
            model.addAttribute("post",postDto);
            return  "admin/edit_post";
        }
        postDto.setId(postId);
        postService.updatePost(postDto);
        return "redirect:/admin/posts";
    }*/
















    /*  @PostMapping("/admin/posts/{postId}")
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
    }*/
}
