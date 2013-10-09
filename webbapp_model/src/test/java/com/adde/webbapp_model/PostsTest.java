package com.adde.webbapp_model;

import com.adde.webbapp_model.TodoPost.Priority;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PostsTest {
    private User user1;
    private User user2;
    private Post post1;
    private WallPost wallPost;
    private TodoPost todoPost;
    private TodoPost todoPost2;
    private GregorianCalendar time1;
    
    private static final String STR1 = "PostContent";
    private static final String STR2 = "EditedPostContent";
    
    @Before
    public void setup(){
        user1 = new User("user1", "email1");
        user2 = new User("user2", "email1");
        time1 = new GregorianCalendar();
        post1 = new Post(user1, STR1);
    }
    
    @Test
    public void PostTest(){
        Post post2 = new Post(user1, STR1);
        assertTrue(post2.getMsg().equals(STR1));
        assertTrue(post2.getAuthor().equals(user1));
        assertTrue(post2.getDateCreated().equals(post2.getDateModified()));
                  
        // Test pointer equality
        Post post3 = post2;
        assertTrue(post2.equals(post3));
        
        // Test null
        Post post4 = null;
        assertFalse(post2.equals(post4));
        
        String toStringBefore = post2.toString();
        int hashCodeBefore = post2.hashCode();
        post2.setMsg(STR2);
        assertFalse(post2.toString().equals(toStringBefore));
        assertTrue(post2.hashCode() == hashCodeBefore);
        
        try {
                Thread.sleep(1);
        } catch (InterruptedException ex) {
                System.out.println("Exception at Thread.sleep(1) in PostsTest!"
                        + "\n" + ex.getMessage());
                System.exit(1);
        }
        post2.setMsg(STR1); //now dateModified should differ form dateCreated!
                            //program is too fast otherwise so it would be
                            //the same millisecond!
        
        assertFalse(post2.getDateCreated().equals(post2.getDateModified()));
        assertTrue(post2.equals(post2));
        assertFalse(post2.equals(post1)); //different id expected
        
        //Check toString
        Logger.getAnonymousLogger().log(Level.INFO, "WallPost: {0}", post2.toString());
    }
    
    @Test
    public void WallPostTest(){
        wallPost = new WallPost(user1, STR1);
        
        assertTrue(wallPost.getDateCreated().equals(wallPost.getDateModified()));
        assertFalse(wallPost.getComments() == null);
        assertTrue(wallPost.getComments().isEmpty());
        
        wallPost.addComment(post1);
        assertTrue(wallPost.getComments().size() == 1);
        assertTrue(wallPost.getComments().get(0).equals(post1));
        
        wallPost.addComment(user2, STR2);
        assertTrue(wallPost.getComments().size() == 2);
        
        wallPost.removeComment(post1);
        assertTrue(wallPost.getComments().size() == 1);
        assertFalse(wallPost.getComments().get(0).equals(post1));
        
        wallPost.removeCommentsByUser(user2);
        assertTrue(wallPost.getComments().isEmpty());
        assertTrue(wallPost.equals(wallPost));
        
        //Check toString
        Logger.getAnonymousLogger().log(Level.INFO, "WallPost: {0}", wallPost.toString());
    }
    
    @Test
    public void TodoPostTest(){
        todoPost = new TodoPost(user1, STR1);
        assertTrue(todoPost.getAssignedTo().isEmpty());
        todoPost.assignTo(user1);
        LinkedList<User> l = new LinkedList<User>();
        l.add(user1);
        l.add(user2);
        todoPost.assignTo(l);
        assertTrue(todoPost.getAssignedTo().size() == 2);
        todoPost.unassign(user2);
        assertTrue(todoPost.getAssignedTo().size() == 1);
        todoPost.clearAssignedTo();
        assertTrue(todoPost.getAssignedTo().isEmpty());
        assertTrue(todoPost.getPriority() == null);
        assertTrue(todoPost.getDeadline() == null);
        todoPost.setDeadline(time1);
        assertTrue(todoPost.getDeadline().equals(time1));
        todoPost.setPriority(Priority.LOW);
        assertTrue(todoPost.getPriority().equals(Priority.LOW));
        assertFalse(todoPost.getPriority().equals(Priority.MEDIUM));
        
        //Checking the other constructor too
        todoPost2 = new TodoPost(user1, STR1, time1);
        assertTrue(todoPost2.getDateCreated() != null);
        
        //Check toString
        Logger.getAnonymousLogger().log(Level.INFO, "TodoPost: {0}", todoPost2.toString());
    }
}