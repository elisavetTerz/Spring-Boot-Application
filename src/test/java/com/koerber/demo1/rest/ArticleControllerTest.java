package com.koerber.demo1.rest;

import com.koerber.demo1.model.Article;
import com.koerber.demo1.service.ArticleManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ArticleControllerTest {

    @Mock
    private ArticleManager articleManager;

    @InjectMocks
    private ArticleController articleController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }

    @Test
    void testCreateArticle() throws Exception {
        Article article = new Article(1, "Test Description", 10.0, 5.0, true);

        when(articleManager.createArticle(any(Article.class))).thenReturn(article);

        mockMvc.perform(post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Test Description\",\"weight\":10.0,\"volume\":5.0,\"active\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Test Description"));

        verify(articleManager, times(1)).createArticle(any(Article.class));
    }

    @Test
    void testUpdateArticle() throws Exception {
        Article article = new Article(1, "Updated Description", 10.0, 5.0, true);
        when(articleManager.updateArticle(any(Article.class))).thenReturn(article);

        mockMvc.perform(put("/api/articles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Updated Description\",\"weight\":10.0,\"volume\":5.0,\"active\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Updated Description"));

        verify(articleManager, times(1)).updateArticle(any(Article.class));
    }

    @Test
    void testDeleteArticle() throws Exception {
        doNothing().when(articleManager).deleteArticle(1);

        mockMvc.perform(delete("/api/articles/1"))
                .andExpect(status().isOk());

        verify(articleManager, times(1)).deleteArticle(1);
    }

}