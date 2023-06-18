package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.UserService;

import javax.validation.Valid;

@Controller
public class UserPage extends Page {
    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String user(@PathVariable long id,
                       Model model) {
        User user = userService.findById(id);
        model.addAttribute("user_profile", user);
        return "UserPage";
    }

    @GetMapping("/user")
    public String userRedirect(){
        return "redirect:/";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String user() {
        return "redirect:/";
    }
}
