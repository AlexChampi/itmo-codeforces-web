package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.UserRegisterForm;
import ru.itmo.wp.form.validator.UserRegisterFormValidator;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class UserController {
    private final JwtService jwtService;
    private final UserService userService;
    private final UserRegisterFormValidator userRegisterFormValidator;

    public UserController(JwtService jwtService, UserService userService, UserRegisterFormValidator userRegisterFormValidator) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.userRegisterFormValidator = userRegisterFormValidator;
    }

    @GetMapping("users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @InitBinder("userRegisterForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userRegisterFormValidator);
    }

    @PostMapping("users")
    public String register(@RequestBody @Valid UserRegisterForm userRegisterForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        User user = userService.register(userRegisterForm);
        return jwtService.create(user);
    }

    @GetMapping("users/auth")
    public User findUserByJwt(@RequestParam String jwt) {
        return jwtService.find(jwt);
    }
}
