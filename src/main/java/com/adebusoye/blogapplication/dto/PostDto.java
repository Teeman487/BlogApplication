package com.adebusoye.blogapplication.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
// Validation page
@Data  // auto generate Getter &Setters method, ToString method, equals and Hashcode methods etc
@Builder // Generate building pattern for this class
@NoArgsConstructor
// we intend to instantiate  handler method to handle new post request  PostDto postDto = new PostDto();
@AllArgsConstructor //
public class PostDto { //Model entity is useful when we want to transport data between Controller layer & View Layer
    private Long id;
    @NotEmpty( message = "post title should not be empty")
    private String title;
    private String url;
    @NotEmpty (message = "content should not be empty")
    private String content;
    @NotEmpty (message = "post short description should not be empty")
    private String briefSummary;

    private LocalDateTime createdOn;
    private LocalDateTime updateOn;

}




    /*@NotEmpty(message = "post title should not be empty")
    private String title;
    private String url;

    @NotEmpty(message = "post content should not be empty")
    private String content;
    @NotEmpty(message = "post short description should not be empty")
    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updateOn;
    private Set<CommentDto> comments; // 82*/
