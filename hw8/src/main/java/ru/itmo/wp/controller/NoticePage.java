package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.form.NoticeCredentials;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/notice")
@Controller
public class NoticePage extends Page {
    private final NoticeService noticeService;

    public NoticePage(NoticeService noticeService) {
        this.noticeService = noticeService;
    }


    @GetMapping
    public String notice(Model model) {
        model.addAttribute("noticeForm", new NoticeCredentials());
        return "NoticePage";
    }

    @PostMapping
    public String notice(@Valid @ModelAttribute("noticeForm") NoticeCredentials noticeForm,
                         BindingResult bindingResult,
                         HttpServletRequest httpRequest) {
        if (bindingResult.hasErrors()) {
            return "NoticePage";
        }
        noticeService.save(noticeForm);
        return "redirect:/notice";
    }
}
