package com.adde.webbapp_model;

import com.adde.webbapp_model.DeadlinePost.Priority;
import java.util.Date;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PostsTest {
    private User user1;
    private User user2;
    private Post post1;
    private WallPost wallPost;
    private DeadlinePost deadlinePost;
    private MilestonePost milestonePost;
    private Date date1;
    private Date date2;
    
    private static final String STR1 = "PostContent";
    private static final String STR2 = "EditedPostContent";
    
    @Before
    public void setup(){
        user1 = new User("user1");
        user2 = new User("user2");
        date1 = new Date(System.currentTimeMillis());
        date2 = new Date(System.currentTimeMillis() + 86400); //+ 24 hours
        post1 = new Post(user1, STR1);
    }
    
    @Test
    public void PostTest(){
        Post post2 = new Post(user1, STR1);
        assertTrue(post2.getMsg().equals(STR1));
        assertTrue(post2.getAuthor().equals(user1));
        assertTrue(post2.getDateCreated().equals(post2.getDateModified()));
        
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
    }
    
    @Test
    public void DeadlinePostTest(){
        deadlinePost = new DeadlinePost(user1, user2, STR1, date1,
                Priority.LOW);
        assertTrue(deadlinePost.getMsg().equals(STR1));
        assertTrue(deadlinePost.getDeadline().equals(date1));
        assertTrue(deadlinePost.getResponsibleUser().equals(user2));
        assertTrue(deadlinePost.getPriority() == Priority.MEDIUM);
        
        deadlinePost.setDeadline(date2);
        assertTrue(deadlinePost.getDeadline().equals(date2));
        
        deadlinePost.setResponsibleUser(user1);
        assertTrue(deadlinePost.getResponsibleUser().equals(user1));
    }
    
    @Test
    public void MilestonePostTest(){
        LinkedList<User> assignedTo1 = new LinkedList<User>();
        LinkedList<User> assignedTo2 = new LinkedList<User>();
        assignedTo2.add(user1);
        assignedTo2.add(user1);
        assignedTo2.add(user1);
        assignedTo2.add(user2);
        
        milestonePost = new MilestonePost(user1, user2, STR1, date1,
                Priority.MEDIUM, assignedTo1);
        assertTrue(milestonePost.getAssignedTo().isEmpty());
        
        milestonePost.setAssignedTo(assignedTo2);
        
        //does not allow duplicates!
        System.out.println("milestonePost.getAssignedTo().size() == " + milestonePost.getAssignedTo().size());
        assertTrue(milestonePost.getAssignedTo().size() == 2);
    }
}