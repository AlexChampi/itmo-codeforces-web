package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused"})
public class LogoutPage extends AbstractPage {
    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        User user = getUser();
        if (user != null) {
            request.getSession().removeAttribute("user");
            setMessage("Good bye. Hope to see you soon!");
        } else {
            setMessage("You are already logout");
        }
        throw new RedirectException("/index");
    }
}
