package ru.itmo.wp.web.page;

import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MyArticlesPage extends AbstractPage {
    @Override
    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        if (getUser() == null) {
            setMessage("You are not authorized.");
            throw new RedirectException("/index");
        }
    }

    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        view.put("articles", articleService.findByUser(getUser().getId()));
    }

    private void update(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        long userId = getUser().getId();
        Long articleId = tryParseLong(request.getParameter("articleId"));
        if (articleId == null) {
            setMessage("Article not found");
            throw new RedirectException("/myArticles");
        }
        boolean isHidden = articleService.validateArticleUpdate(userId, articleId);
        articleService.updateHidden(articleId, isHidden);
        view.put("articleStatus", isHidden);
    }
}
