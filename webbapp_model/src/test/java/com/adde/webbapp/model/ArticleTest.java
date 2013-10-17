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
    Article article1, article2;
    Person person1, person2;
    
    @Before
    public void before() {
        daoFactory = DAOFactory.getDAOFactory();
        person1 = new Person("person1", "email1", "pass1");
        daoFactory.getPersonDAO().add(person1);
        person2 = new Person("person2", "email2", "pass1");
        daoFactory.getPersonDAO().add(person2);

        article1 = new Article("content1", "title1");
        article2 = new Article("content2", "title2");
        
        //Check
        assertTrue(daoFactory.getPersonDAO().getAll().size() == 2);
        assertTrue(daoFactory.getArticleDAO().getAll().isEmpty());
    }
    
    @After
    public void after() {
        daoFactory.getPersonDAO().remove(person1.getId());
        daoFactory.getPersonDAO().remove(person2.getId());
        assertTrue(daoFactory.getPersonDAO().getAll().isEmpty());
    }
    
    @Test
    public void addAndAuthorContent() {
        daoFactory.getArticleDAO().add(article1);
        Article articleFromDB = daoFactory.getArticleDAO().find(article1.getId());
        Logger.getAnonymousLogger().log(Level.INFO, "Article1:{0}", articleFromDB.toString());
        
        //Correct Content?
        assertTrue(articleFromDB.getContent().equals("content1"));
        
        //Clean
        daoFactory.getArticleDAO().remove(articleFromDB.getId());
    }
    
    @Test
    public void testUpdatedEditorsAndFind() {/*
        daoFactory.getArticleDAO().add(article1);
        
        //New Editor
        Article articleFromDB = daoFactory.getArticleDAO().find(article1.getId());
        articleFromDB.setContent("updatedContent1");
        articleFromDB.setTitle("updatedTitle1");
        //Persist new SimpleEditorEntry
        SimpleEditorEntry see = new SimpleEditorEntry(person2);
        daoFactory.getSimpleEditorEntryDAO().add(see);
        //Add New SimpleEditorEntry to Article
        List<SimpleEditorEntry> editorEntry = articleFromDB.getEditorEntries();
        editorEntry.add(see);
        daoFactory.getArticleDAO().update(article1);
        
        // Check if the updated content is correct
        articleFromDB = daoFactory.getArticleDAO().find(article1.getId());
        assertTrue(articleFromDB.getTitle().equals("updateTitle1"));
        assertTrue(articleFromDB.getContent().equals("updatedContent1"));*/
        
        // Check if the user has been added to the editors
        /*daoFactory.getArticleDAO().update(article1);
        assertTrue(article1.getEditors().contains(person1));
        assertFalse(article1.getEditors().contains(person2));
        
        // Check if the last editor is first in the list of editors.
        assertTrue(article1.getEditors().get(0).equals(person1));
        
        //Clean
        daoFactory.getArticleDAO().remove(article1.getId());*/
    }
    /*
    @Ignore
    @Test
    public void testEquality() {
        Article a1 = new Article(new Person("Author 1", "Aemail1"), "test equals", "authorTitle1");
        
        // Pointer equality
        Article a2 = a1;
        assertTrue(a2.equals(a1));
        
        // Object equality
        Object a3 = (Object) a1;
        assertTrue(a1.equals(a3));
        
        // Same content, shouldn't be equal
        Article a4 = new Article(new Person("Author 4", "aemail4"), "test equals", "authorTitle4");
        assertFalse(a1.equals(a4));
        
        // Test Collection compability (depends on hashcode)
        HashSet<Article> coll = new HashSet<Article>();
        coll.add(a1);
        assertTrue(coll.contains(a1));
        
        //NullPointerTest
        a3 = null;
        assertFalse(a2.equals(a3));
        
        //Same id test
        Article a5 = new Article(article1.getId(), article1.getEditors().get(0), article1.getContent(), article1.getTitle());
        assertTrue(a5.equals(article1));
        
        //Same id but different authors, should still be equal since equal-method just checks id
        a5.update(user2, a5.getContent(), a5.getTitle());
    }
    
    // Just for checking the printed output, not really a test
    @Ignore
    @Test
    public void printArticle() {
        Article a1 = new Article(new Person("Author 1", "Aemail1"), "test equals", "authorTitle1");
        System.out.println(a1.toString());
    }*/
}