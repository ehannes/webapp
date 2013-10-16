package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Article;
import com.adde.webbapp.model.entity.SimpleEditorEntry;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

    @Override
    public void add(Article article) {
        final Calendar time = new GregorianCalendar();
        article.setDateCreated(time);
        article.setDateModified(time);
        super.add(article);
    }
}