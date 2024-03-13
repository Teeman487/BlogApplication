package com.adebusoye.blogapplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;  // comment
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
//
    @ManyToOne  //  many comments for one post
    @JoinColumn(name = "post_id", nullable = false) // post_id is a foreign key to table post(id) // foreign key (post_id)
    private Post post; // indicate (one) post

}
