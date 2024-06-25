package com.koerber.demo1.service;

import com.koerber.demo1.model.Article;
import com.koerber.demo1.repository.ArticleRepository;
import com.koerber.demo1.service.exceptions.ArticleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleManagerImplTest {


    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleManagerImpl articleManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateArticleSuccessful() {
        Article article = new Article(null, "Test Description", 10.0, 5.0, true);
        Article savedArticle = new Article(1, "Test Description", 10.0, 5.0, true);

        when(articleRepository.save(article)).thenReturn(savedArticle);

        Article result = articleManager.createArticle(article);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(articleRepository, times(1)).save(article);
    }

    @Test
    void testUpdateArticleSuccessful() {
        Article article = new Article(1, "Updated Description", 10.0, 5.0, true);
        when(articleRepository.findById(1)).thenReturn(Optional.of(article));
        when(articleRepository.save(any())).thenReturn(article);

        Article result = articleManager.updateArticle(article);

        assertNotNull(result);
        assertEquals("Updated Description", result.getDescription());
        verify(articleRepository, times(1)).findById(1);
        verify(articleRepository, times(1)).save(article);
    }

    @Test
    void testDeleteArticleSuccessful() {
        Article article = new Article(1, "Description", 10.0, 5.0, true);
        when(articleRepository.findById(1)).thenReturn(Optional.of(article));
        doNothing().when(articleRepository).deleteById(1);

        articleManager.deleteArticle(1);

        verify(articleRepository, times(1)).deleteById(1);
    }

    @Test
    void testUpdateArticleThrowsArticleNotFoundException() {
        Article articleToUpdate = new Article(1, "Updated Article", 10.0, 5.0, true);
        when(articleRepository.findById(articleToUpdate.getId())).thenReturn(Optional.empty());

        assertThrows(ArticleNotFoundException.class, () -> articleManager.updateArticle(articleToUpdate));
        verify(articleRepository, never()).save(articleToUpdate);
    }


    @Test
    void testDeleteArticleThrowsArticleNotFoundException() {
        when(articleRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ArticleNotFoundException.class, () -> articleManager.deleteArticle(any()));
        verify(articleRepository, never()).deleteById(any());
    }
}