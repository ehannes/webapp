/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import java.util.Date;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PostsTest {
    User user1;
    User user2;
    Post post;
    WallPost wpost;
    MilestonePost mspost;
    LinkedList<User> assignedTo;
    
    String content1 = "PostContent";
    
    
    @Before
    public void setup(){
        assignedTo = new LinkedList();
        assignedTo.add(user1);
        assignedTo.add(new User("user2"));
        user1 = new User("Author1");
        post = new Post(user1, content1);
        wpost = new WallPost(user1, content1);
        mspost = new MilestonePost(user1, user1, content1, new Date(),
                1, assignedTo);
    }
    
    @Test
    public void PostTest(){
        assertTrue(post.getMsg().equals(content1));
        assertTrue(post.getAuthor().equals(user1));
        assertTrue(post.getDateCreated() == post.getDateModified());
        post.setMsg(content1);
        assertFalse(post.getDateCreated() == post.getDateModified());
    }
}
