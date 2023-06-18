package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.form.CommentForm;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/post")
public class PostPage extends Page {
    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @Guest
    @GetMapping("/{postId}")
    public String post(@PathVariable long postId,
                       Model model,
                       HttpSession session) {
        Post post = postService.findById(postId);
        if (post == null) {
            return exception(session);
        }
        model.addAttribute("post", post);
        model.addAttribute("commentForm", new CommentForm());
        return "PostPage";
    }


    @PostMapping("/{postId}")
    public String comment(@PathVariable long postId,
                          @Valid @ModelAttribute("commentForm") CommentForm commentForm,
                          BindingResult bindingResult,
                          HttpSession session,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postService.findById(postId));
            return "PostPage";
        }
        postService.addComment(postId, getUser(session), commentForm);
        return "redirect:/post/{postId}";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String exception(HttpSession session) {
        putMessage(session, "Post not found");
        return "redirect:/";
    }
}
