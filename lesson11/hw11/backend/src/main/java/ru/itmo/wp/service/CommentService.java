package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.form.CommentWriteForm;
import ru.itmo.wp.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    private final JwtService jwtService;

    public CommentService(CommentRepository commentRepository, JwtService jwtService) {
        this.commentRepository = commentRepository;
        this.jwtService = jwtService;
    }

    public List<Comment> getAll () {
        return commentRepository.findAll();
    }

    public Comment write(CommentWriteForm commentWriteForm) {
        Comment comment = new Comment();
        comment.setUser(jwtService.find(commentWriteForm.getJwt()));
        comment.setPost(commentWriteForm.getPost());
        comment.setText(commentWriteForm.getText());
        return commentRepository.save(comment);
    }
}
