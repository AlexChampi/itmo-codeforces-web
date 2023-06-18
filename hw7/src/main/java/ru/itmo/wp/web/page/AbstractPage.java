package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class AbstractPage {
    protected final UserService userService = new UserService();

    protected final ArticleService articleService = new ArticleService();

    private HttpServletRequest request;

    public void before(HttpServletRequest request, Map<String, Object> view) {
        putUser(request, view);
        putMessage(request, view);
        this.request = request;
        view.put("userCount", userService.findCount());
    }

    public void after(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }


    protected void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void putUser(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            view.put("user", user);
        }
    }

    private void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    protected void setMessage(String message) {
        request.getSession().setAttribute("message", message);
    }

    protected void setUser(User user) {
        request.getSession().setAttribute("user", user);
    }

    protected User getUser() {
        return (User) request.getSession().getAttribute("user");
    }

    protected Long tryParseLong(String number) {
        if (number == null) {
            return null;
        }
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            return null;
        }
    }


}
