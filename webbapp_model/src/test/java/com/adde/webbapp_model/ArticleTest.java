package com.adde.webbapp_model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
    public void testEquality() {
        Article a1 = new Article(new User("Author 1"), "test equals");
        
        // Pointer equality
        Article a2 = a1;
        assertTrue(a2.equals(a1));
        
        // Object equality
        Object a3 = (Object) a1;
        assertTrue(a1.equals(a3));
        
        // Same content, shouldn't be equal
        Article a4 = new Article(new User("Author 4"), "test equals");
        assertFalse(a1.equals(a4));
        
        // Test Collection compability (depends on hashcode)
        HashSet<Article> coll = new HashSet<Article>();
        coll.add(a1);
        assertTrue(coll.contains(a1));
    }
}