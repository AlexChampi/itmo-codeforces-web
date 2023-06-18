package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.CommentWriteForm;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.PostService;

@Component
public class CommentWriteValidator implements Validator {
    private final JwtService jwtService;

    private final PostService postService;

    public CommentWriteValidator(JwtService jwtService, PostService postService) {
        this.jwtService = jwtService;
        this.postService = postService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CommentWriteForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            CommentWriteForm commentWriteForm = (CommentWriteForm) target;
            if (jwtService.find(commentWriteForm.getJwt()) == null) {
                errors.reject("undefined-jwt", "Unknown user");
            } else if (postService.findById(commentWriteForm.getPost().getId()) == null) {
                errors.reject("undefined-post", "Unknown post");
            }
        }
    }
}
