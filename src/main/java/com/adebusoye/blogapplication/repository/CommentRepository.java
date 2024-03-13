package com.adebusoye.blogapplication.repository;

import com.adebusoye.blogapplication.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
     @Query(value = "select c.* from comments c inner join post p\n" +
            "where c.post_id = p.id and p.created_by =:userId", nativeQuery = true)
     List<Comment> findCommentsByPost(Long userId);
    // 108 Refactor Admin Side List Comments Feature

     /* @Query("SELECT p from Post p WHERE " +
            " p.title LIKE CONCAT('%', :query, '%') OR " +
            " p.briefSummary LIKE CONCAT('%', :query, '%')")
          // JPQL is Java Persistence Query Language defined in JPA specification. It is used to create queries against entities to store relational datbase
   List<Post> searchPosts(String query);*/

}
