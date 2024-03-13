package com.adebusoye.blogapplication.controller;

import com.adebusoye.blogapplication.dto.PostDto;
import com.adebusoye.blogapplication.dto.RegistrationDto;


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

    UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


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
                           Model model){
//       User existingUser = userService.findByEmail(userReg.getEmail());   //92  // email: ade4@gmail.com  //
//        if(existingUser != null && existingUser.getEmail() !=null && !existingUser.getEmail().isEmpty()){
//            result.rejectValue("email", null, "There is already a user with same email id");
//        }

        if(result.hasErrors()){
            model.addAttribute("userReg", userReg);
            return "register";
        }
        userService.saveUser(userReg);
        return "redirect:/register?success"; // to show the success message
    }

}


