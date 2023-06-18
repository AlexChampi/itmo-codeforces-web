package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused"})
public class UsersPage extends AbstractPage {
    @Override
    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        if (getUser() != null) {
            setUser(userService.find(getUser().getId()));
            view.put("user", getUser());
        }
    }

    @Override
    public void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
        if (getUser() != null) {
            setUser(userService.find(getUser().getId()));
            view.put("user", getUser());
        }
    }

    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

    private void findUser(HttpServletRequest request, Map<String, Object> view) {
        view.put("user",
                userService.find(Long.parseLong(request.getParameter("userId"))));
    }

    private void admin(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        Long userId = tryParseLong(request.getParameter("userId"));
        if (userId == null) {
            setMessage("User not found");
            throw new RedirectException("/users");
        }
        if (getUser() == null) {
            setMessage("You are not authorized");
            throw new RedirectException("/index");
        }
        User user = userService.validateAdmin(userId, getUser());
        userService.setAdmin(user.getId(), !user.isAdmin());
        view.put("userStatus", !user.isAdmin());
    }
}
