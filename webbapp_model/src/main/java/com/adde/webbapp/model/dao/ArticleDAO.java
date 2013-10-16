package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Article;

/**
 * @author ehannes
 */
public class ArticleDAO extends AbstractDAO<Article,Long> {
    public static ArticleDAO newInstance(){
        return new ArticleDAO();
    }
    
    private ArticleDAO() {
        super(Article.class);
    }
}