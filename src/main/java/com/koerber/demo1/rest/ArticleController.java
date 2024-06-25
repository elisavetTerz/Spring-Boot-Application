package com.koerber.demo1.rest;

import com.koerber.demo1.model.Article;
import com.koerber.demo1.service.ArticleManager;
import com.koerber.demo1.service.exceptions.ArticleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleManager articleManager;

    @Autowired
    public ArticleController(ArticleManager articleManager) {
        this.articleManager = articleManager;
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article createdArticle =  articleManager.createArticle(article);
        return ResponseEntity.ok(createdArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Integer id, @RequestBody Article article) {
        article.setId(id);
        Article updatedArticle = articleManager.updateArticle(article);
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Integer id) {
        articleManager.deleteArticle(id);
        return ResponseEntity.ok("Deleted Article with ID: " + id);
    }
}
