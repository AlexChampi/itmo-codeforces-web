package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.CommentForm;
import ru.itmo.wp.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public Post findById(Long postId) {
        return postId == null ? null : postRepository.findById(postId).orElse(null);
    }

    public void addComment(long postId, User user, CommentForm commentForm) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null && user != null) {
            Comment comment = new Comment();
            comment.setPost(post);
            comment.setUser(user);
            comment.setText(commentForm.getComment());
            post.addComment(comment);
            postRepository.save(post);
        }

    }
}
