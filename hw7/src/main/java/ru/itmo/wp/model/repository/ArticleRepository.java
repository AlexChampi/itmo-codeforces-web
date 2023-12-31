package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

import java.util.List;

public interface ArticleRepository {
    void save(Article article);

    void updateHidden(long articleId, boolean isHidden);

    List<Article> findAll();

    Article find(long articleId);

    List<Article> findByUser(long userId);
}
