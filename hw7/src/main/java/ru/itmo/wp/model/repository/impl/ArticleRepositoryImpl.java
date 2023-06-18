package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.repository.ArticleRepository;

import java.util.List;

public class ArticleRepositoryImpl extends AbstractBasicRepository<Article> implements ArticleRepository {
    public ArticleRepositoryImpl() {
        super("Article", Article::new);
    }

    @Override
    public void save(Article article) {
        save(article, "INSERT INTO `Article` (`userId`, `title`, `text`, `hidden`, `creationTime`)" +
                        " VALUES (?, ?, ?, TRUE, NOW())",
                article.getUserId(), article.getTitle(), article.getText());
    }

    @Override
    public void updateHidden(long articleId, boolean isHidden) {
        update("UPDATE `Article` SET hidden=? WHERE id=?", isHidden, articleId);
    }


    @Override
    public List<Article> findAll() {
        return findAllWithParams("SELECT * FROM Article ORDER BY creationTime DESC");
    }

    @Override
    public Article find(long articleId) {
        return findByParams("SELECT * FROM Article WHERE id=?", articleId);
    }

    @Override
    public List<Article> findByUser(long userId) {
        return findAllWithParams("SELECT * FROM Article WHERE userId=?", userId);
    }
}
