package com.adde.webbapp_model;

import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * Test for Article.
 * @author hannes
 */
public class ArticleTest {
    
    Article article1, article2;
    User user1, user2;
    
    @Before
    public void before() {
        user1 = new User("user1");
        user2 = new User("user2");
        article1 = new Article(user1, "content1");
        article2 = new Article(user2, "content2");
    }
    
    @Test
    public void testAuthorContent() {
        Logger.getAnonymousLogger().log(Level.INFO, "Article1:{0}", article1.toString());
        
        //Correct Author and Content?
        assertTrue(article1.getAuthor().equals(user1));
        assertTrue(article1.getContent().equals("content1"));
        
        //Printing dates
        Logger.getAnonymousLogger().log(Level.INFO, "Article created: {0} and modified {1}", new Object[]{article1.getDateCreated(), article1.getDateModified()});
    }
    
    @Test
    public void testUpdatedEditors() {
        article1.update(user1, "updatedcontent1");
        
        // Check if the updated content is correct
        assertTrue(article1.getContent().equals("updatedcontent1"));
        
        // Check if the user has been added to the editors
        assertTrue(article1.getEditEntries().containsValue(user1));
        
    }
    
    @Test
    public void testCreatedModified() {
    }
}