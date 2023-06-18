package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.PostWriteForm;
import ru.itmo.wp.form.validator.PostWriteValidator;
import ru.itmo.wp.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class PostController {
    private final PostService postService;
    private final PostWriteValidator postWriteValidator;

    public PostController(PostService postService, PostWriteValidator postWriteValidator) {
        this.postService = postService;
        this.postWriteValidator = postWriteValidator;
    }

    @GetMapping("posts")
    public List<Post> findPosts() {
        return postService.findAll();
    }

    @InitBinder("postWriteForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(postWriteValidator);
    }

    @PostMapping("posts")
    public Post writePost(@RequestBody @Valid PostWriteForm postWriteForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        return postService.write(postWriteForm);
    }
}
