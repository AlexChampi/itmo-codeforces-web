package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.UserChangeStatusCredentials;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @PostMapping("/users/change-status")
    public String changeStatus(@Valid @ModelAttribute("changeStatusForm") UserChangeStatusCredentials changeStatusForm,
                               BindingResult bindingResult,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "redirect:/users/all";
        }
        boolean newStatus = "disable".equals(changeStatusForm.getNewStatus());
        userService.changeStatus(newStatus, changeStatusForm.getUserId());
        User user = getUser(httpSession);
        if (user != null && user.getId() == changeStatusForm.getUserId()) {
            user = userService.findById(changeStatusForm.getUserId());
            setUser(httpSession, user);
            if (newStatus) {
                unsetUser(httpSession);
                setMessage(httpSession, "User is unable");
            }
        }
        return "redirect:/users/all";
    }
}
