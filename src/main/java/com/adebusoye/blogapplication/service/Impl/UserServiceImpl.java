package com.adebusoye.blogapplication.service.Impl;

import com.adebusoye.blogapplication.dto.RegistrationDto;
import com.adebusoye.blogapplication.entity.Role;
import com.adebusoye.blogapplication.entity.User;
import com.adebusoye.blogapplication.repository.RoleRepository;
import com.adebusoye.blogapplication.repository.UserRepository;
import com.adebusoye.blogapplication.service.UserService;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;

   PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

        @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
//      user.setPassword(registrationDto.getPassword());
        // use spring security to encrypt the password
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword())); //100

        Role role = roleRepository.findByName("ROLE_GUEST"); // FIND Role object from the database
       user.setRoles(Arrays.asList(role)); // private List<Role> roles =new ArrayList<>();
        userRepository.save(user); // user entity


    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email); // repository locate a particular email as id
    }
}
