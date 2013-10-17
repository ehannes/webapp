package com.adde.webbapp.model;

import com.adde.webbapp.model.dao.ArticleDAO;
import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PersonDAO;
import com.adde.webbapp.model.dao.SimpleEditorEntryDAO;
import com.adde.webbapp.model.entity.Article;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.SimpleEditorEntry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ArticleTest {
    DAOFactory daoFactory;
    PersonDAO personDAO;
    ArticleDAO articleDAO;
    SimpleEditorEntryDAO seeDAO;
    Article article1, article2;
    Person person1, person2;
    
    @Before
    public void before() {
        Logger.getAnonymousLogger().log(Level.INFO, "----TEST---- Before test!");
        daoFactory = DAOFactory.getDAOFactory();
        personDAO = daoFactory.getPersonDAO();
        articleDAO = daoFactory.getArticleDAO();
        seeDAO = daoFactory.getSimpleEditorEntryDAO();
        person1 = new Person("person1", "email1", "pass1");
        personDAO.add(person1);
        person2 = new Person("person2", "email2", "pass1");
        personDAO.add(person2);

        article1 = new Article("title1", "content1");
        article2 = new Article("title2", "content2");
        
        //Check
        assertTrue(personDAO.getAll().size() == 2);
        assertTrue(articleDAO.getAll().isEmpty());
    }
    
    @After
    public void after() {
        personDAO.remove(person1.getId());
        personDAO.remove(person2.getId());
        assertTrue(personDAO.getAll().isEmpty());
        Logger.getAnonymousLogger().log(Level.INFO, "----TEST---- After test!");
    }
    
    @Test
    public void addAndAuthorContent() {
        Logger.getAnonymousLogger().log(Level.INFO, "----TEST---- addAndAuthorContent!");
        articleDAO.add(article1);
        Article articleFromDB = articleDAO.find(article1.getId());
        Logger.getAnonymousLogger().log(Level.INFO, "Article1:{0}", articleFromDB.toString());
        
        //Correct Content?
        assertTrue(articleFromDB.getContent().equals("content1"));
        
        //Clean
        articleDAO.remove(articleFromDB.getId());
        assertTrue(articleDAO.getAll().isEmpty());
    }
    
    @Test
    public void testUpdatedEditorsAndFind() {
        Logger.getAnonymousLogger().log(Level.INFO, "----TEST---- testUpdatedEditorsAndFind!");
        articleDAO.add(article1);
        
        //New Editor
        Article articleFromDB = articleDAO.find(article1.getId());
        articleFromDB.setContent("updatedContent1");
        articleFromDB.setTitle("updatedTitle1");
        //Persist new SimpleEditorEntry
        SimpleEditorEntry see = new SimpleEditorEntry(person2);
        seeDAO.add(see);
        //Add New SimpleEditorEntry to Article
        List<SimpleEditorEntry> editorEntry = articleFromDB.getEditorEntries();
        editorEntry.add(see);
        
        articleDAO.update(articleFromDB);
        
        // Check if the updated content is correct
        articleFromDB = articleDAO.find(article1.getId());
        assertTrue(articleFromDB.getTitle().equals("updatedTitle1"));
        assertTrue(articleFromDB.getContent().equals("updatedContent1"));
        
        // Check if the user has been added to the editors
        articleDAO.update(articleFromDB);
        articleFromDB = articleDAO.find(article1.getId());
        List<SimpleEditorEntry> editorEntries = articleFromDB.getEditorEntries();
        assertTrue(editorEntries.size() == 1);
        assertTrue(editorEntries.get(0).getEditor().equals(person2));
        
        //getLatestEntry not working yet...
        /*Logger.getAnonymousLogger().log(Level.INFO, "----TEST---- Before getLastEntry.................");
        List<SimpleEditorEntry> simpleEditorEntry = articleDAO.getLatestEntry(article1);
        Logger.getAnonymousLogger().log(Level.INFO, "----TEST---- After getLastEntry.................");
        assertTrue(simpleEditorEntry != null);
        assertTrue(simpleEditorEntry.get(0).getEditor().equals(person2));*/

        //Clean
        articleDAO.remove(article1.getId());
        assertTrue(articleDAO.getAll().isEmpty());
        Logger.getAnonymousLogger().log(Level.INFO, "----TEST---- End testUpdatedEditorsAndFind!");
    }
    
    // Just for checking the printed output, not really a test
    @Test
    public void printArticle() {
        Logger.getAnonymousLogger().log(Level.INFO, "----TEST---- printArticle!");
        articleDAO.add(article1);
        Article articleFromDB = articleDAO.find(article1.getId());
        System.out.println(articleFromDB.toString());
        
        //Clean
        articleDAO.remove(article1.getId());
        assertTrue(articleDAO.getAll().isEmpty());
        Logger.getAnonymousLogger().log(Level.INFO, "----TEST---- End printArticle!");
    }
}