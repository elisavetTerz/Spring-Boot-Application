package com.koerber.demo1.repository;

import com.koerber.demo1.model.Article;
import java.util.Optional;


public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> findById(Integer id);
    void deleteById(Integer id);
}
