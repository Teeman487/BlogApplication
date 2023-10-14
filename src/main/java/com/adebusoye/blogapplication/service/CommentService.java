package com.adebusoye.blogapplication.service;

import com.adebusoye.blogapplication.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void createComment(String postUrl, CommentDto commentDto);  //void createPost(PostDto postDto);
    /*th:action="@{/{postUrl}/comments(postUrl=${post.url})}"
                                th:object="${comment}"*/



    // 83
    List<CommentDto> findCommentsByPost();

    List<CommentDto> findAllComments();

    void deleteComment(Long commentId);
}
