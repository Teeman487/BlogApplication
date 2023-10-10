package com.adebusoye.blogapplication.repository;

import com.adebusoye.blogapplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findByUrl(String url);
    @Query("SELECT p from Post p WHERE " +
            " p.title LIKE CONCAT('%', :query, '%') OR " +
            " p.briefSummary LIKE CONCAT('%', :query, '%')")  // JPQL is Java Persistence Query Language defined in JPA specification. It is used to create queries against entities to store relational datbase
    List<Post> searchPosts (String query);

}
