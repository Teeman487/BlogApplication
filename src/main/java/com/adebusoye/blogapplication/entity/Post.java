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
public class Post {
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
}
