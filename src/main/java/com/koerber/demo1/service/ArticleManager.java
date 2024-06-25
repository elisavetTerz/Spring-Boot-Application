package com.koerber.demo1.service;

import com.koerber.demo1.model.Article;
import com.koerber.demo1.service.exceptions.ArticleNotFoundException;

public interface ArticleManager {
    Article createArticle(Article article);
    Article updateArticle(Article article) throws ArticleNotFoundException;
    void deleteArticle(Integer articleId) throws ArticleNotFoundException;
}
