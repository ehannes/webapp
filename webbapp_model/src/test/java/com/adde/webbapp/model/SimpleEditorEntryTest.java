package com.adde.webbapp.model;

import com.adde.webbapp.model.dao.PersonDAO;
import com.adde.webbapp.model.dao.SimpleEditorEntryDAO;
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
 *
 * @author ehannes
 */
public class SimpleEditorEntryTest {
    SimpleEditorEntryDAO seeDAO;
    SimpleEditorEntry see;
    PersonDAO personDAO;
    ArrayList<String> nameList;
    ArrayList<Person> editorList;
    final int NR_OF_EDITORS = 20;
    
    @Before
    public void before() {
        //Creating editors
        nameList = new ArrayList<>();
        for(int i = 0; i < NR_OF_EDITORS; i++){
            nameList.add("editor" + i);
        }
        
        editorList = new ArrayList<>();
        for(int i = 0; i < NR_OF_EDITORS; i++){
            editorList.add(new Person(nameList.get(i), "mail@mail.com", "hajk89"));
        }
        
        personDAO = PersonDAO.newInstance();
        for(int i = 0; i < NR_OF_EDITORS; i++){
            personDAO.add(editorList.get(i));
        }
        
        //Creating SimpleEditorEntry
        seeDAO = SimpleEditorEntryDAO.newInstance();
        
        
        //No SimpleEditorEntry at start
        assertTrue(personDAO.getAll().size() == NR_OF_EDITORS);
    }
    
    @After //Remember to remove all SimpleEditorEntries at end of all tests!
    public void after() {
        for(int i = 0; i < NR_OF_EDITORS; i++){
            personDAO.remove(editorList.get(i).getId());
        }
        
        //No SimpleEditorEntry after
        assertTrue(seeDAO.getAll().isEmpty());
    } 
    
    @Test
    public void entryFunctions() {
        see = new SimpleEditorEntry(editorList.get(0));
        seeDAO.add(see);
        
        //Fetch from DB
        SimpleEditorEntry seeFromDB = seeDAO.find(see.getId());
        long id = seeFromDB.getId();
        assertTrue(seeFromDB.getEditor().equals(editorList.get(0)));
        
        //Equality
        assertTrue(seeFromDB.equals(see));
        
        //Change editor, update and check if editor still changed
        seeFromDB.setEditor(editorList.get(1));
        seeDAO.update(seeFromDB);
        seeFromDB = seeDAO.find(id);
        assertFalse(seeFromDB.getEditor().equals(editorList.get(0)));
        
        //Date rounds to day. Will not see any difference in time here...
        Logger.getAnonymousLogger().log(Level.INFO, "Modified: {0}", see.getModificationTime());
        seeFromDB.setModificationTime(new GregorianCalendar());
        seeDAO.update(seeFromDB);
        seeDAO.find(id);
        Logger.getAnonymousLogger().log(Level.INFO, "Modified: {0}", see.getModificationTime());
        
        //Clean
        seeDAO.remove(id);
    }
    
    @Test
    public void simplePersist() {
        //Add 1
        see = new SimpleEditorEntry(editorList.get(0));
        seeDAO.add(see);
        
        //Find
        SimpleEditorEntry seeFromDatabase = seeDAO.find(see.getId());
        assertTrue(seeFromDatabase.equals(see));
        
        //GetAll
        List<SimpleEditorEntry> seeList = seeDAO.getAll();
        assertTrue(seeList.size() == 1);
        
        //Remove
        seeDAO.remove(see.getId());
        assertTrue(seeDAO.getAll().isEmpty());
    }
    
    @Test
    public void addMultiples() {
        for(int i = 0; i < NR_OF_EDITORS; i++){
            seeDAO.add(new SimpleEditorEntry(editorList.get(i)));
        }
        
        assertTrue(seeDAO.getAll().size() == NR_OF_EDITORS);
        
        List<SimpleEditorEntry> allEntries = seeDAO.getAll();
        for(SimpleEditorEntry simpleEditorEntry : allEntries) {
            seeDAO.remove(simpleEditorEntry.getId());
        }
        assertTrue(seeDAO.getAll().isEmpty());
    }
}