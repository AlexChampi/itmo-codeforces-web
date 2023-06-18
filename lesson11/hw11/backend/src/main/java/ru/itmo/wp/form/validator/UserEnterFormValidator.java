package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.UserEnterForm;
import ru.itmo.wp.service.UserService;

@Component
public class UserEnterFormValidator implements Validator {
    private final UserService userService;

    public UserEnterFormValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return UserEnterForm.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserEnterForm enterForm = (UserEnterForm) target;
            if (userService.findByLoginAndPassword(enterForm.getLogin(), enterForm.getPassword()) == null) {
                errors.reject("invalid-login-or-password", "Invalid login or password");
            }
        }
    }
}
