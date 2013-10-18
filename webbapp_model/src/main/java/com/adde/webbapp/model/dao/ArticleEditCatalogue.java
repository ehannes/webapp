package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.ArticleEdit;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
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

    /*public List<SimpleEditorEntry> getEditEntriesByPerson(Person editor) {
        List<SimpleEditorEntry> found = new ArrayList<>();
        for(ArticleEdit entry : getAll()){
            if(entry.getEditor().equals(editor)){
                found.add(entry);
            }
        }
        return found;
    }
    
    public List<SimpleEditorEntry> getEditEntries(int n) {
        List<SimpleEditorEntry> result = new ArrayList<>();
        
        return result;
    }*/
}