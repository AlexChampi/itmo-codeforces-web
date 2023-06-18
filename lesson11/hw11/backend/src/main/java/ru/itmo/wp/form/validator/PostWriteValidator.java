package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.PostWriteForm;
import ru.itmo.wp.service.JwtService;

@Component
public class PostWriteValidator implements Validator {
    private final JwtService jwtService;

    public PostWriteValidator(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return PostWriteForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            PostWriteForm postWriteForm = (PostWriteForm) target;
            if (jwtService.find(postWriteForm.getJwt()) == null) {
                errors.reject("undefined-jwt", "Unknown user");
            }
        }
    }
}
