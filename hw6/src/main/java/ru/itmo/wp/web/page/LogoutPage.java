package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused"})
public class LogoutPage extends AbstractPage {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        User user = getUser();
        if (user != null) {
            Event event = new Event();
            event.setType(Event.Type.LOGOUT);
            event.setUserId(user.getId());
            eventService.save(event);
            request.getSession().removeAttribute("user");
            setMessage("Good bye. Hope to see you soon!");
        }
        throw new RedirectException("/index");
    }
}
