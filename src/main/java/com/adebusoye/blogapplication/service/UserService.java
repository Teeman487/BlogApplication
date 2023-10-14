package com.adebusoye.blogapplication.service;

import com.adebusoye.blogapplication.dto.RegistrationDto;
import com.adebusoye.blogapplication.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    User findByEmail(String email);
}
