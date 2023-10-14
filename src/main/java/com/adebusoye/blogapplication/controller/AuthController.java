package com.adebusoye.blogapplication.controller;

import com.adebusoye.blogapplication.dto.PostDto;
import com.adebusoye.blogapplication.dto.RegistrationDto;
import com.adebusoye.blogapplication.entity.User;
import com.adebusoye.blogapplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

@Autowired
UserService userService;


    // handler method to handle login page request
    @GetMapping("/login")
    public String loginPage(){    /*<a th:href="@{/login}"> Login Here</a>*/
        return "login";
    }
    // handler method to handle user registration request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        //this object holds from data
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("userReg", user);
        return "register";

    }


    // handler method to handel user registration form submit request 91
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("userReg") RegistrationDto userReg, // @Valid //92
                           BindingResult result,
                           Model model){/* @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email); // repository locate a particular email
    }*/
       User existingUser = userService.findByEmail(userReg.getEmail()); //92  // email: ade4@gmail.com  //
        if(existingUser != null && existingUser.getEmail() !=null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null, "There is already a user with same email id");
        }

        if(result.hasErrors()){
            model.addAttribute("userReg", userReg);
            return "register";
        }
        userService.saveUser(userReg);
        return "redirect:/register?success"; // to show the success message
    }
}

/*@PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable("postUrl") String postUrl,
                                 @Valid @ModelAttribute("comment") CommentDto commentDto,
                                BindingResult result, // 81
                                Model model){ //@Valid //81

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
    }*/


/*@PostMapping("/admin/post")
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
    }*/