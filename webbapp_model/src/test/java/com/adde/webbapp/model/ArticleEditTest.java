package com.adde.webbapp.model;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.ArticleEdit;
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
public class ArticleEditTest {
    DAOFactory daoFactory;
    ArticleEdit see;
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
        
        //No ArticleEdit at start
        assertTrue(daoFactory.getPersonDAO().getAll().size() == NR_OF_EDITORS);
    }
    
    @After //Remember to remove all SimpleEditorEntries at end of all tests!
    public void after() {
        for(int i = 0; i < NR_OF_EDITORS; i++){
            daoFactory.getPersonDAO().remove(editorList.get(i).getId());
        }
        
        //No ArticleEdit after
        assertTrue(daoFactory.getSimpleEditorEntryDAO().getAll().isEmpty());
    } 
    
    @Test
    public void entryFunctions() {
        see = new ArticleEdit(editorList.get(0));
        daoFactory.getSimpleEditorEntryDAO().add(see);
        
        //Fetch from DB
        ArticleEdit seeFromDB = daoFactory.getSimpleEditorEntryDAO().find(see.getId());
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
        Logger.getAnonymousLogger().log(Level.INFO, "Modified: {0}", see.getEditTime());
        seeFromDB.setEditTime(new GregorianCalendar());
        daoFactory.getSimpleEditorEntryDAO().update(seeFromDB);
        daoFactory.getSimpleEditorEntryDAO().find(id);
        Logger.getAnonymousLogger().log(Level.INFO, "Modified: {0}", see.getEditTime());
        
        //Clean
        daoFactory.getSimpleEditorEntryDAO().remove(id);
    }
    
    @Test
    public void simplePersist() {
        //Add 1
        see = new ArticleEdit(editorList.get(0));
        daoFactory.getSimpleEditorEntryDAO().add(see);
        
        //Find
        ArticleEdit seeFromDatabase = daoFactory.getSimpleEditorEntryDAO().find(see.getId());
        assertTrue(seeFromDatabase.equals(see));
        
        //GetAll
        List<ArticleEdit> seeList = daoFactory.getSimpleEditorEntryDAO().getAll();
        assertTrue(seeList.size() == 1);
        
        //Remove
        daoFactory.getSimpleEditorEntryDAO().remove(see.getId());
        assertTrue(daoFactory.getSimpleEditorEntryDAO().getAll().isEmpty());
    }
    
    @Test
    public void addMultiples() {
        for(int i = 0; i < NR_OF_EDITORS; i++){
            daoFactory.getSimpleEditorEntryDAO().add(new ArticleEdit(editorList.get(i)));
        }
        
        assertTrue(daoFactory.getSimpleEditorEntryDAO().getAll().size() == NR_OF_EDITORS);
        
        List<ArticleEdit> allEntries = daoFactory.getSimpleEditorEntryDAO().getAll();
        for(ArticleEdit simpleEditorEntry : allEntries) {
            daoFactory.getSimpleEditorEntryDAO().remove(simpleEditorEntry.getId());
        }
        assertTrue(daoFactory.getSimpleEditorEntryDAO().getAll().isEmpty());
    }
}