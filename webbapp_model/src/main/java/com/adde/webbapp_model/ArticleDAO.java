package com.adde.webbapp_model;

import com.adde.webbapp_model_util.AbstractDAO;

/**
 * @author ehannes
 */
public class ArticleDAO extends AbstractDAO<Article,Long> {
    public ArticleDAO(String puName) {
        super(Article.class, puName);
    }
    
}