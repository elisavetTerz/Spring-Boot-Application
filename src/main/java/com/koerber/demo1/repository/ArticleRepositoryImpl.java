package com.koerber.demo1.repository;

import com.koerber.demo1.model.Article;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private final Map<Integer, Article> articleStorage = new HashMap<>();
    private int currentId = 1;

    @Override
    public Article save(Article article) {
        if (article.getId() == null) {
            article.setId(currentId++);
        }
        articleStorage.put(article.getId(), article);
        return article;
    }

    @Override
    public Optional<Article> findById(Integer id) {
        return Optional.ofNullable(articleStorage.get(id));
    }

    @Override
    public void deleteById(Integer id) {
        articleStorage.remove(id);
    }
}
