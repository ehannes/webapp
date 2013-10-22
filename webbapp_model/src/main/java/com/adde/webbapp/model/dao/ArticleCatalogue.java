package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Article;

/**
 * @author ehannes
 */
public class ArticleCatalogue extends AbstractDAO<Article,Long> {
    public static ArticleCatalogue newInstance(){
        return new ArticleCatalogue();
    }
    
    private ArticleCatalogue() {
        super(Article.class);
    }
}