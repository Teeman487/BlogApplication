package com.adebusoye.blogapplication.service;

import com.adebusoye.blogapplication.dto.CommentDto;

import java.util.List;

public interface CommentService {
  //  void createComment(String postUrl, CommentDto commentDto);  //void createPost(PostDto postDto);

    List<CommentDto> findAllComments();

    void createComment(String postUrl, CommentDto commentDto);

    void deleteComment(Long commentId);
    /*th:action="@{/{postUrl}/comments(postUrl=${post.url})}"
                                th:object="${comment}"*/



    // 83
//    List<CommentDto> findCommentsByPost();
}
