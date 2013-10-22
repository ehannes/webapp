package com.adde.webbapp.model;

import com.adde.webbapp.model.dao.ArticleCatalogue;
import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PersonCatalogue;
import com.adde.webbapp.model.dao.ArticleEditCatalogue;
import com.adde.webbapp.model.dao.EMFUtil;
import com.adde.webbapp.model.entity.Article;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.ArticleEdit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArticleTest {
    DAOFactory daoFactory;
    PersonCatalogue personCatalogue;
    ArticleCatalogue articleCatalogue;
    ArticleEditCatalogue articleEditCatalogue;
    Article article1, article2;
    Person person1, person2;
    
    @BeforeClass
    public static void createEMF() {
        EMFUtil.createEntityManagerFactory();
    }
    
    @AfterClass
    public static void destroyEMF() {
        EMFUtil.destroyEntityManagerFactory();
    }
    
    @Before
    public void before() {
        daoFactory = DAOFactory.getDAOFactory();
        
        //Init Articles and persons
        personCatalogue = daoFactory.getPersonCatalogue();
        articleCatalogue = daoFactory.getArticleCatalogue();
        articleEditCatalogue = daoFactory.getArticleEditCatalogue();
        person1 = new Person("person1", "email1", "pass1");
        personCatalogue.add(person1);
        person2 = new Person("person2", "email2", "pass1");
        personCatalogue.add(person2);

        article1 = new Article("title1", "content1");
        article2 = new Article("title2", "content2");
        
        //Check
        assertTrue(personCatalogue.getAll().size() == 2);
        assertTrue(articleCatalogue.getAll().isEmpty());
    }
    
    @After
    public void after() {
        personCatalogue.remove(person1.getId());
        personCatalogue.remove(person2.getId());
        
        assertTrue(personCatalogue.getAll().isEmpty());
    }
    
    @Test
    public void addArticleAndContent() {
        articleCatalogue.add(article1);
        Article articleFromDB = articleCatalogue.find(article1.getId());
        Logger.getAnonymousLogger().log(Level.INFO, "Article1:{0}", articleFromDB.toString());
        
        //Correct Content?
        assertTrue(articleFromDB.getContent().equals("content1"));
        
        //Clean
        articleCatalogue.remove(articleFromDB.getId());
        assertTrue(articleCatalogue.getAll().isEmpty());
    }
    
    @Test
    public void testUpdatedEditorsAndFind() {
        articleCatalogue.add(article1);
        
        //New Editor
        Article articleFromDB = articleCatalogue.find(article1.getId());
        articleFromDB.setContent("updatedContent1");
        articleFromDB.setTitle("updatedTitle1");
        //Persist new ArticleEdit
        ArticleEdit see = new ArticleEdit(person2);
        articleEditCatalogue.add(see);
        //Add New ArticleEdit to Article
        List<ArticleEdit> editorEntry = articleFromDB.getArticleEditions();
        editorEntry.add(see);
        
        articleCatalogue.update(articleFromDB);
        
        // Check if the updated content is correct
        articleFromDB = articleCatalogue.find(article1.getId());
        assertTrue(articleFromDB.getTitle().equals("updatedTitle1"));
        assertTrue(articleFromDB.getContent().equals("updatedContent1"));
        
        // Check if the user has been added to the editors
        articleCatalogue.update(articleFromDB);
        articleFromDB = articleCatalogue.find(article1.getId());
        List<ArticleEdit> editorEntries = articleFromDB.getArticleEditions();
        assertTrue(editorEntries.size() == 1);
        assertTrue(editorEntries.get(0).getEditor().equals(person2));

        //Clean
        articleCatalogue.remove(article1.getId());
        assertTrue(articleCatalogue.getAll().isEmpty());
    }
    
    @Test // Just for checking the printed output, not really a test
    public void printArticle() {
        articleCatalogue.add(article1);
        Article articleFromDB = articleCatalogue.find(article1.getId());
        System.out.println(articleFromDB.toString());
        
        //Clean
        articleCatalogue.remove(article1.getId());
        assertTrue(articleCatalogue.getAll().isEmpty());
    }
}