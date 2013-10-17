package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Article;
import com.adde.webbapp.model.entity.SimpleEditorEntry;
import java.util.List;

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
    
    /*public List<SimpleEditorEntry> getLatestEntry(Article article) {
        List<SimpleEditorEntry> simpleEditorEntry = getEntityManager().createQuery(
                "SELECT a.editorEntries FROM Article a ORDER BY a.editorEntries.modificationTime DESC", SimpleEditorEntry.class)
                .setMaxResults(1).getResultList();
        return simpleEditorEntry;
    }*/
}