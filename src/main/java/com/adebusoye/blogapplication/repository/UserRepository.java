package com.adebusoye.blogapplication.repository;

import     com.adebusoye.blogapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
User findByEmail(String email);


}