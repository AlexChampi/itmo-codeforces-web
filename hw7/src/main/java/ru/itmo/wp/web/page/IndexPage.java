package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused"})
public class IndexPage extends AbstractPage {
    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        view.put("articles", articleService.findAll());
        Map<Long, String> userLoginByUserId = new HashMap<>();
        for (User user : userService.findAll()) {
            userLoginByUserId.put(user.getId(), user.getLogin());
        }
        view.put("userLoginByUserId", userLoginByUserId);
    }
}
