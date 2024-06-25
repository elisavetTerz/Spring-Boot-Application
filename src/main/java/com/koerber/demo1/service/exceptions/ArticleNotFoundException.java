package com.koerber.demo1.service.exceptions;

public class ArticleNotFoundException  extends RuntimeException {

    public ArticleNotFoundException(Integer id) {
        super("Article with id: " + id + " not found");
    }
}
