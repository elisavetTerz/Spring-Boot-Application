package com.koerber.demo1.service;

import com.koerber.demo1.model.Article;
import com.koerber.demo1.repository.ArticleRepository;
import com.koerber.demo1.rest.ArticleController;
import com.koerber.demo1.service.exceptions.ArticleNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ArticleManagerImpl implements ArticleManager {

    private final ArticleRepository articleRepository;
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    public ArticleManagerImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article createArticle(Article article) {
        Article createdArticle = articleRepository.save(article);
        logger.info("Created Article: {}", createdArticle);
        return createdArticle;
    }

    @Override
    public Article updateArticle(Article article) throws ArticleNotFoundException {
        return articleRepository.findById(article.getId())
                .map(existingArticle -> {
                    Article updatedArticle = articleRepository.save(article);
                    logger.info("Updated Article: {}", updatedArticle);
                    return updatedArticle;
                })
                .orElseThrow(() -> new ArticleNotFoundException(article.getId()));
    }

    @Override
    public void deleteArticle(Integer id) throws ArticleNotFoundException {
        Optional<Article> article = articleRepository.findById(id);
        if (articleRepository.findById(id).isPresent()) {
            articleRepository.deleteById(id);
            logger.info("Deleted Article: {}", article);
        } else {
            throw new ArticleNotFoundException(id);
        }
    }
}
