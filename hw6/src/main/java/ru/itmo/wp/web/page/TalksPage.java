package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.domain.dto.TalkDTO;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class TalksPage extends AbstractPage {
    private final TalkService talkService = new TalkService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        if (getUser() == null) {
            setMessage("You are not authorized.");
            throw new RedirectException("/index");
        }
        putView(request, view);
    }

    private void sendMessage(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        action(request, view);
        User targetUser = userService.findByLogin(request.getParameter("targetUser"));
        User sourceUser = getUser();
        String text = request.getParameter("text");
        talkService.validateMessage(targetUser, sourceUser, text);
        Talk talk = new Talk();
        talk.setSourceUserId(sourceUser.getId());
        talk.setTargetUserId(targetUser.getId());
        talk.setText(text);
        talkService.save(talk);

        throw new RedirectException("/talks");
    }

    private void putView(HttpServletRequest request, Map<String, Object> view) {
        view.put("text", request.getParameter("text"));
        view.put("users", userService.findAll());
        List<Talk> talks = talkService.findAllWithUser(getUser().getId());
        List<TalkDTO> talksDTO = talks.stream().map(
                (talk) -> new TalkDTO(
                        talk.getId(),
                        userService.find(talk.getSourceUserId()).getLogin(),
                        userService.find(talk.getTargetUserId()).getLogin(),
                        talk.getText(),
                        talk.getCreationTime())).toList();

        view.put("talks", talksDTO);
    }
}

