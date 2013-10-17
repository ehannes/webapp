package com.adde.webbapp.model;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.SimpleEditorEntry;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ehannes
 */
public class SimpleEditorEntryTest {
    DAOFactory daoFactory;
    SimpleEditorEntry see;
    ArrayList<String> nameList;
    ArrayList<Person> editorList;
    final int NR_OF_EDITORS = 20;
    
    @Before
    public void before() {
        daoFactory = DAOFactory.getDAOFactory();
        //Creating editors
        nameList = new ArrayList<>();
        for(int i = 0; i < NR_OF_EDITORS; i++){
            nameList.add("editor" + i);
        }
        
        editorList = new ArrayList<>();
        for(int i = 0; i < NR_OF_EDITORS; i++){
            editorList.add(new Person(nameList.get(i), "mail@mail.com", "hajk89"));
        }
        
        for(int i = 0; i < NR_OF_EDITORS; i++){
            daoFactory.getPersonDAO().add(editorList.get(i));
        }
        
        //No SimpleEditorEntry at start
        assertTrue(daoFactory.getPersonDAO().getAll().size() == NR_OF_EDITORS);
    }
    
    @After //Remember to remove all SimpleEditorEntries at end of all tests!
    public void after() {
        for(int i = 0; i < NR_OF_EDITORS; i++){
            daoFactory.getPersonDAO().remove(editorList.get(i).getId());
        }
        
        //No SimpleEditorEntry after
        assertTrue(daoFactory.getSimpleEditorEntryDAO().getAll().isEmpty());
    } 
    
    @Test
    public void entryFunctions() {
        see = new SimpleEditorEntry(editorList.get(0));
        daoFactory.getSimpleEditorEntryDAO().add(see);
        
        //Fetch from DB
        SimpleEditorEntry seeFromDB = daoFactory.getSimpleEditorEntryDAO().find(see.getId());
        long id = seeFromDB.getId();
        assertTrue(seeFromDB.getEditor().equals(editorList.get(0)));
        
        //Equality
        assertTrue(seeFromDB.equals(see));
        
        //Change editor, update and check if editor still changed
        seeFromDB.setEditor(editorList.get(1));
        daoFactory.getSimpleEditorEntryDAO().update(seeFromDB);
        seeFromDB = daoFactory.getSimpleEditorEntryDAO().find(id);
        assertFalse(seeFromDB.getEditor().equals(editorList.get(0)));
        
        //Date rounds to day. Will not see any difference in time here...
        Logger.getAnonymousLogger().log(Level.INFO, "Modified: {0}", see.getModificationTime());
        seeFromDB.setModificationTime(new GregorianCalendar());
        daoFactory.getSimpleEditorEntryDAO().update(seeFromDB);
        daoFactory.getSimpleEditorEntryDAO().find(id);
        Logger.getAnonymousLogger().log(Level.INFO, "Modified: {0}", see.getModificationTime());
        
        //Clean
        daoFactory.getSimpleEditorEntryDAO().remove(id);
    }
    
    @Test
    public void simplePersist() {
        //Add 1
        see = new SimpleEditorEntry(editorList.get(0));
        daoFactory.getSimpleEditorEntryDAO().add(see);
        
        //Find
        SimpleEditorEntry seeFromDatabase = daoFactory.getSimpleEditorEntryDAO().find(see.getId());
        assertTrue(seeFromDatabase.equals(see));
        
        //GetAll
        List<SimpleEditorEntry> seeList = daoFactory.getSimpleEditorEntryDAO().getAll();
        assertTrue(seeList.size() == 1);
        
        //Remove
        daoFactory.getSimpleEditorEntryDAO().remove(see.getId());
        assertTrue(daoFactory.getSimpleEditorEntryDAO().getAll().isEmpty());
    }
    
    @Test
    public void addMultiples() {
        for(int i = 0; i < NR_OF_EDITORS; i++){
            daoFactory.getSimpleEditorEntryDAO().add(new SimpleEditorEntry(editorList.get(i)));
        }
        
        assertTrue(daoFactory.getSimpleEditorEntryDAO().getAll().size() == NR_OF_EDITORS);
        
        List<SimpleEditorEntry> allEntries = daoFactory.getSimpleEditorEntryDAO().getAll();
        for(SimpleEditorEntry simpleEditorEntry : allEntries) {
            daoFactory.getSimpleEditorEntryDAO().remove(simpleEditorEntry.getId());
        }
        assertTrue(daoFactory.getSimpleEditorEntryDAO().getAll().isEmpty());
    }
}