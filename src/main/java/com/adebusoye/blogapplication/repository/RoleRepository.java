package com.adebusoye.blogapplication.repository;

import com.adebusoye.blogapplication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name); // because of   Role role = roleRepository.findByName("ROLE_GUEST"); // get Role object from the database
    //Optional<Post> findByUrl(String url);
}
