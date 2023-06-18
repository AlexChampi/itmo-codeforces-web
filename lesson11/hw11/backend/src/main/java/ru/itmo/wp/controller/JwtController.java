package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.UserEnterForm;
import ru.itmo.wp.form.validator.UserEnterFormValidator;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/1")
public class JwtController {
    private final JwtService jwtService;
    private final UserService userService;
    private final UserEnterFormValidator userEnterFormValidator;

    public JwtController(JwtService jwtService, UserService userService, UserEnterFormValidator userEnterFormValidator) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.userEnterFormValidator = userEnterFormValidator;
    }

    @InitBinder("userEnterForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userEnterFormValidator);
    }

    @PostMapping("jwt")
    public String create(@RequestBody @Valid UserEnterForm userEnterForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        User user = userService.findByLoginAndPassword(userEnterForm.getLogin(), userEnterForm.getPassword());
        return jwtService.create(user);
    }
}
