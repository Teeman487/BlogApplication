package com.adebusoye.blogapplication.service.Impl;

import com.adebusoye.blogapplication.dto.RegistrationDto;
import com.adebusoye.blogapplication.entity.Role;
import com.adebusoye.blogapplication.entity.User;
import com.adebusoye.blogapplication.repository.RoleRepository;
import com.adebusoye.blogapplication.repository.UserRepository;
import com.adebusoye.blogapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        // use spring security to encrypt the password
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword())); //100
        //user.setPassword(registrationDto.getPassword());
        Role role = roleRepository.findByName("ROLE_GUEST"); // get Role object from the database
        user.setRoles(Arrays.asList(role)); //
        userRepository.save(user); // user entity

        /*@Override
    public void createPost(PostDto postDto) {     //createPost will redirect to Repository data-Post-Entity
        Post post = PostMapper.mapToPost(postDto);  // map PostDto to  Post entity
        postRepository.save(post); // createPost saved in Post Entity
    }*/
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email); // repository locate a particular email
    }
}/*@Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }*/
