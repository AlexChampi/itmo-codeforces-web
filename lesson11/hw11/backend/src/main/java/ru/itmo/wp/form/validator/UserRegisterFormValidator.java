package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.UserEnterForm;
import ru.itmo.wp.form.UserRegisterForm;
import ru.itmo.wp.service.UserService;

@Component
public class UserRegisterFormValidator implements Validator {
    private final UserService userService;

    public UserRegisterFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegisterForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserRegisterForm registerForm = (UserRegisterForm) target;
            if (userService.findByLogin(registerForm.getLogin()) != null) {
                errors.reject("this-login-already-exist", "This login already exist");
            }
        }
    }
}
