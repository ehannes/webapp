package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.SimpleEditorEntry;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;

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

    @Override
    public void add(SimpleEditorEntry simpleEditorEntry) {
        simpleEditorEntry.setModificationTime(new GregorianCalendar());
        super.add(simpleEditorEntry);
    }

    /*public List<SimpleEditorEntry> getEditEntriesByPerson(Person editor) {
        List<SimpleEditorEntry> found = new ArrayList<>();
        for(SimpleEditorEntry entry : getAll()){
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