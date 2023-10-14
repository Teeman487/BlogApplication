package com.adebusoye.blogapplication.mapper;

import com.adebusoye.blogapplication.dto.PostDto;
import com.adebusoye.blogapplication.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {
    // map Post entity to PostDto
    public static PostDto mapToPostDto(Post post){
        // PostDto  postDto = PostDto.builder()
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .briefSummary(post.getBriefSummary())
                .createdOn(post.getCreatedOn())
                .updateOn(post.getUpdateOn())
                .comments(post.getComments().stream()
                        .map(comment -> CommentMapper.mapToCommentDto(comment))
                        .collect(Collectors.toSet()))
                .build();
    }
    // map PostDto to  Post entity
    public static Post mapToPost(PostDto postDto){
        // PostDto  postDto = PostDto.builder()
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .url(postDto.getUrl())
                .content(postDto.getContent())
                .briefSummary(postDto.getBriefSummary())
                .createdOn(postDto.getCreatedOn())
                .updateOn(postDto.getUpdateOn())
                .build();
    }
}
