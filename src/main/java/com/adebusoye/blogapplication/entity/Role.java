package com.adebusoye.blogapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = false)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();  // many users for 1 ROLE_ADMIN, 2: ROLE_GUEST
}


/*  @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE) // post is the owner of the relationship  // Cascade: Whenever we delete the Blog post the hibernate should delete the comment
    private Set<Comment> comments = new HashSet<>();
    // multiple post belongs to 1 user and 1 User can write multiple posts*/