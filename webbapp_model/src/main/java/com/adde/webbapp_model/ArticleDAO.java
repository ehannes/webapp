package com.adde.webbapp_model;

import com.adde.webbapp_model_util.AbstractDAO;

/**
 * @author ehannes
 */
public class ArticleDAO extends AbstractDAO<Article,Long> {
    public static ArticleDAO newInstance(){
        return new ArticleDAO();
    }
    
    private ArticleDAO() {
        super(Article.class, ProjectPlatform.PU);
    }
}