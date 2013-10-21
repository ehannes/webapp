//package com.adde.webbapp.model;
//
//import com.adde.webbapp.model.dao.DAOFactory;
//import com.adde.webbapp.model.entity.Person;
//import com.adde.webbapp.model.entity.ArticleEdit;
//import java.util.ArrayList;
//import java.util.GregorianCalendar;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.junit.After;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// * @author ehannes
// */
//public class ArticleEditTest {
//    DAOFactory daoFactory;
//    ArticleEdit articleEdit;
//    ArrayList<String> nameList;
//    ArrayList<Person> editorList;
//    final int NR_OF_EDITORS = 20;
//    
//    @Before
//    public void before() {
//        daoFactory = DAOFactory.getDAOFactory();
//        //Creating editors
//        nameList = new ArrayList<>();
//        for(int i = 0; i < NR_OF_EDITORS; i++){
//            nameList.add("editor" + i);
//        }
//        
//        editorList = new ArrayList<>();
//        for(int i = 0; i < NR_OF_EDITORS; i++){
//            editorList.add(new Person(nameList.get(i), "mail@mail.com", "hajk89"));
//        }
//        
//        for(int i = 0; i < NR_OF_EDITORS; i++){
//            daoFactory.getPersonCatalogue().add(editorList.get(i));
//        }
//        
//        //No ArticleEdit at start
//        assertTrue(daoFactory.getPersonCatalogue().getAll().size() == NR_OF_EDITORS);
//    }
//    
//    @After //Remember to remove all SimpleEditorEntries at end of all tests!
//    public void after() {
//        for(int i = 0; i < NR_OF_EDITORS; i++){
//            daoFactory.getPersonCatalogue().remove(editorList.get(i).getId());
//        }
//        
//        //No ArticleEdit after
//        assertTrue(daoFactory.getArticleEditCatalogue().getAll().isEmpty());
//    } 
//    
//    @Test
//    public void entryFunctions() {
//        articleEdit = new ArticleEdit(editorList.get(0));
//        daoFactory.getArticleEditCatalogue().add(articleEdit);
//        
//        //Fetch from DB
//        ArticleEdit articleEditFromDB = daoFactory.getArticleEditCatalogue().find(articleEdit.getId());
//        long id = articleEditFromDB.getId();
//        assertTrue(articleEditFromDB.getEditor().equals(editorList.get(0)));
//        
//        //Equality
//        assertTrue(articleEditFromDB.equals(articleEdit));
//        
//        //Change editor, update and check if editor still changed
//        articleEditFromDB.setEditor(editorList.get(1));
//        daoFactory.getArticleEditCatalogue().update(articleEditFromDB);
//        articleEditFromDB = daoFactory.getArticleEditCatalogue().find(id);
//        assertFalse(articleEditFromDB.getEditor().equals(editorList.get(0)));
//        
//        //Date rounds to day. Will not articleEdit any difference in time here...
//        Logger.getAnonymousLogger().log(Level.INFO, "Modified: {0}", articleEdit.getEditTime());
//        articleEditFromDB.setEditTime(new GregorianCalendar());
//        daoFactory.getArticleEditCatalogue().update(articleEditFromDB);
//        daoFactory.getArticleEditCatalogue().find(id);
//        Logger.getAnonymousLogger().log(Level.INFO, "Modified: {0}", articleEdit.getEditTime());
//        
//        //Clean
//        daoFactory.getArticleEditCatalogue().remove(id);
//    }
//    
//    @Test
//    public void simplePersist() {
//        //Add 1
//        articleEdit = new ArticleEdit(editorList.get(0));
//        daoFactory.getArticleEditCatalogue().add(articleEdit);
//        
//        //Find
//        ArticleEdit seeFromDatabase = daoFactory.getArticleEditCatalogue().find(articleEdit.getId());
//        assertTrue(seeFromDatabase.equals(articleEdit));
//        
//        //GetAll
//        List<ArticleEdit> seeList = daoFactory.getArticleEditCatalogue().getAll();
//        assertTrue(seeList.size() == 1);
//        
//        //Remove
//        daoFactory.getArticleEditCatalogue().remove(articleEdit.getId());
//        assertTrue(daoFactory.getArticleEditCatalogue().getAll().isEmpty());
//    }
//    
//    @Test
//    public void addMultiples() {
//        for(int i = 0; i < NR_OF_EDITORS; i++){
//            daoFactory.getArticleEditCatalogue().add(new ArticleEdit(editorList.get(i)));
//        }
//        
//        assertTrue(daoFactory.getArticleEditCatalogue().getAll().size() == NR_OF_EDITORS);
//        
//        List<ArticleEdit> allEntries = daoFactory.getArticleEditCatalogue().getAll();
//        for(ArticleEdit simpleEditorEntry : allEntries) {
//            daoFactory.getArticleEditCatalogue().remove(simpleEditorEntry.getId());
//        }
//        assertTrue(daoFactory.getArticleEditCatalogue().getAll().isEmpty());
//    }
//}