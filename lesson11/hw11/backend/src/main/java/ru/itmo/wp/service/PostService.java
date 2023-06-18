package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.form.PostWriteForm;
import ru.itmo.wp.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final JwtService jwtService;

    public PostService(PostRepository postRepository, JwtService jwtService) {
        this.postRepository = postRepository;
        this.jwtService = jwtService;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public Post findById (long id) {
        return postRepository.findById(id);
    }

    public Post write(PostWriteForm postWriteForm) {
        Post post = new Post();
        post.setUser(jwtService.find(postWriteForm.getJwt()));
        post.setTitle(postWriteForm.getTitle());
        post.setText(postWriteForm.getText());
        return postRepository.save(post);
    }
}
