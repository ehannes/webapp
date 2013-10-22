package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.ArticleEdit;
import java.util.GregorianCalendar;

/**
 * @author ehannes
 */
public class ArticleEditCatalogue extends AbstractDAO<ArticleEdit, Long> {

    public static ArticleEditCatalogue newInstance() {
        return new ArticleEditCatalogue();
    }

    private ArticleEditCatalogue() {
        super(ArticleEdit.class);
    }

    @Override
    public void add(ArticleEdit simpleEditorEntry) {
        simpleEditorEntry.setEditTime(new GregorianCalendar());
        super.add(simpleEditorEntry);
    }
}