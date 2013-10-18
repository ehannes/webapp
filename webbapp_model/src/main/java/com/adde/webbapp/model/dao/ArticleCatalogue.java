package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Article;
import com.adde.webbapp.model.entity.ArticleEdit;
import java.util.List;

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
    
    /*public List<SimpleEditorEntry> getLatestEntry(Article article) {
        List<SimpleEditorEntry> simpleEditorEntry = getEntityManager().createQuery(
                "SELECT a.editorEntries FROM Article a ORDER BY a.editorEntries.modificationTime DESC", ArticleEdit.class)
                .setMaxResults(1).getResultList();
        return simpleEditorEntry;
    }*/
}