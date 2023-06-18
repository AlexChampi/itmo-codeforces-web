package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public Article validateArticle(String title, String text, long userId) throws ValidationException {
        if (Strings.isNullOrEmpty(title) && title.trim().isEmpty()) {
            throw new ValidationException("Title is required");
        }
        if (title.length() > 255) {
            throw new ValidationException("Title must be no longer than 255 characters");
        }
        if (title.length() < 3) {
            throw new ValidationException("Title can't be shorter than 3 characters");
        }

        if (Strings.isNullOrEmpty(text) && text.trim().isEmpty()) {
            throw new ValidationException("Text is required");
        }
        if (text.length() > 1500) {
            throw new ValidationException("Text must be no longer that 1500 characters");
        }

        Article article = new Article();
        article.setUserId(userId);
        article.setTitle(title);
        article.setText(text);
        return article;
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void updateHidden(long articleId, boolean isHidden) {
        articleRepository.updateHidden(articleId, isHidden);
    }

    public boolean validateArticleUpdate(long userId, long articleId) throws ValidationException {
        Article article = findArticle(articleId);
        if (article == null) {
            throw new ValidationException("Article with id " + articleId + " not found");
        }
        if (article.getUserId() != userId) {
            throw new ValidationException("You don't have permission to this article");
        }

        return !article.isHidden();
    }

    public Article findArticle(long articleId) {
        return articleRepository.find(articleId);
    }

    public List<Article> findByUser(long userId) {
        return articleRepository.findByUser(userId);
    }
}
