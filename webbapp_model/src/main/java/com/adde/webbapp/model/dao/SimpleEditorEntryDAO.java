package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Article;
import com.adde.webbapp.model.entity.SimpleEditorEntry;
import java.util.GregorianCalendar;

/**
 *
 * @author ehannes
 */
public class SimpleEditorEntryDAO extends AbstractDAO<SimpleEditorEntry, Long> {

    public static SimpleEditorEntryDAO newInstance() {
        return new SimpleEditorEntryDAO();
    }

    private SimpleEditorEntryDAO() {
        super(SimpleEditorEntry.class);
    }

    public void add(SimpleEditorEntry simpleEditorEntry) {
        simpleEditorEntry.setModificationTime(new GregorianCalendar());
        super.add(simpleEditorEntry);
    }
}