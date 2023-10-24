package com.adebusoye.blogapplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
// Database Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="post")
public class Post { // Post is parent
    @Id  // specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key Generated strategy; identity behind the scene uses database providing Auto increment feature; when hibernate enters or insert the record, then database will Auto increment the primary key and store in the dataBaseTable
    private Long id;
    @Column(nullable = false)
    private String title;
    private String url;
    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;
    private String briefSummary;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updateOn;


    @ManyToOne   // 104    Many posts for 1 created_by
    @JoinColumn(name = "created_by", nullable = false) // we have created foreign key to Users table
    private User createdBy; // drop comments, posts, users, users_roles
    //primary key of User table become the foreign key for Post table4

    /*  @ManyToOne  // Multiple comments in post_id of comments rep (one) post(id) of Post Entity //
    @JoinColumn(name = "post_id", nullable = false) // post_id is a foreign key to table post(id) // foreign key (post_id)
    private Post post; // indicate (one) post*/

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE) // post is the owner of the relationship  // Cascade: Whenever we delete the Blog post the hibernate should delete the comment
    private Set<Comment> comments = new HashSet<>(); // many comments

}
/**/