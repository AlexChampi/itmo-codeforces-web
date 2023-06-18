package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.CommentWriteForm;
import ru.itmo.wp.form.validator.CommentWriteValidator;
import ru.itmo.wp.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class CommentController {
    private final CommentService commentService;
    private final CommentWriteValidator commentWriteValidator;

    public CommentController(CommentService commentService, CommentWriteValidator commentWriteValidator) {
        this.commentService = commentService;
        this.commentWriteValidator = commentWriteValidator;
    }

    @GetMapping("comments")
    public List<Comment> getUsers() {
        return commentService.getAll();
    }

    @InitBinder("commentWriteForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(commentWriteValidator);
    }

    @PostMapping("comments")
    public Comment writeComment(@RequestBody @Valid CommentWriteForm commentWriteForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        return commentService.write(commentWriteForm);
    }
}
