package com.adebusoye.blogapplication.service.Impl;

import com.adebusoye.blogapplication.dto.CommentDto;
import com.adebusoye.blogapplication.entity.Comment;
import com.adebusoye.blogapplication.entity.Post;
import com.adebusoye.blogapplication.entity.User;
import com.adebusoye.blogapplication.mapper.CommentMapper;
import com.adebusoye.blogapplication.repository.CommentRepository;
import com.adebusoye.blogapplication.repository.PostRepository;
import com.adebusoye.blogapplication.repository.UserRepository;
import com.adebusoye.blogapplication.service.CommentService;
import com.adebusoye.blogapplication.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public void createComment(String postUrl, CommentDto commentDto) {

        Post post = postRepository.findByUrl(postUrl).get();  // repository will find Post entity by URL
        Comment comment = CommentMapper.mapToComment(commentDto);  // map CommentDto to Comment entity
        comment.setPost(post); // I.E Comment(post_id): links PostURL(url)
        commentRepository.save(comment); // save the comment to database
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);
        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());

    }
    /* @Query(value = "select c.* from comments c inner join post p\n" +
            "where c.post_id = p.id and p.created_by =:userId", nativeQuery = true)
            many comments for one post and post created
     List<Comment> findCommentsByPost(Long userId);*/

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
    /* @Override
    public List<PostDto> findAllPosts() { // PostDto serves as Model
        List<Post>posts=postRepository.findAll(); //FETCHING DATA FROM ENTITY-DATABASE
        return posts.stream().map( PostMapper::mapToPostDto) // Post Entity mapping PostDto
                .collect(Collectors.toList());
    }*/
}
