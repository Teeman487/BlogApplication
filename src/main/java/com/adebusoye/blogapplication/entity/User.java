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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade =CascadeType.ALL) // multiple comments rep user_id & role_id
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns =  {@JoinColumn(name = "role_id", referencedColumnName = "id")}

    )
    private List<Role> roles =new ArrayList<>(); // indicate more than one role
}
/*
    @ManyToOne  // Multiple comments in post_id of comments rep (one) post(id) of Post Entity //
    @JoinColumn(name = "post_id", nullable = false) // post_id is a foreign key to table post(id) // foreign key (post_id)
    private Post post;*/