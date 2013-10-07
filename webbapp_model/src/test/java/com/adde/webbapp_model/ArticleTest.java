package com.adde.webbapp_model;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ArticleTest {
    
    Article article1, article2;
    User user1, user2;
    
    @Before
    public void before() {
        user1 = new User("user1");
        user2 = new User("user2");
        article1 = new Article(user1, "content1", "title1");
        article2 = new Article(user2, "content2", "title2");
    }
    
    @Test
    public void testAuthorContent() {
        Logger.getAnonymousLogger().log(Level.INFO, "Article1:{0}", article1.toString());
        
        //Correct Author and Content?
        assertTrue(article1.getAuthor().equals(user1));
        assertFalse(article1.getAuthor().equals(user2));
        assertTrue(article1.getContent().equals("content1"));
        assertFalse(article1.getContent().equals("content12"));
        
        //Printing dates
        Logger.getAnonymousLogger().log(Level.INFO, "Article created: {0} and modified {1}", new Object[]{article1.getDateCreated(), article1.getDateModified()});
    }
    
    @Test
    public void testUpdatedEditors() {
        article1.update(user1, "updatedContent1", "updateTitle1");
        
        // Check if the updated content is correct
        assertTrue(article1.getContent().equals("updatedContent1"));
        assertTrue(article1.getTitle().equals("updateTitle1"));
        
        // Check if the user has been added to the editors
        assertTrue(article1.getEditors().contains(user1));
        assertFalse(article1.getEditors().contains(user2));
        
        // Check if the last editor is first in the list of editors.
        assertTrue(article1.getEditors().get(0).equals(user1));
    }
    
    @Test
    public void testEquality() {
        Article a1 = new Article(new User("Author 1"), "test equals", "authorTitle1");
        
        // Pointer equality
        Article a2 = a1;
        assertTrue(a2.equals(a1));
        
        // Object equality
        Object a3 = (Object) a1;
        assertTrue(a1.equals(a3));
        
        // Same content, shouldn't be equal
        Article a4 = new Article(new User("Author 4"), "test equals", "authorTitle4");
        assertFalse(a1.equals(a4));
        
        // Test Collection compability (depends on hashcode)
        HashSet<Article> coll = new HashSet<Article>();
        coll.add(a1);
        assertTrue(coll.contains(a1));
        
        //NullPointerTest
        a3 = null;
        assertFalse(a2.equals(a3));
        
        //Same id test
        Article a5 = new Article(article1.getId(), article1.getAuthor(), article1.getContent(), article1.getTitle());
        assertTrue(a5.equals(article1));
        
        //Same id but different authors, should still be equal since equal-method just checks id
        a5.update(user2, a5.getContent(), a5.getTitle());
    }
}