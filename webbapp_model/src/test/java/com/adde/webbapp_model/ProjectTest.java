/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import com.adde.webbapp_model.DeadlinePost.Priority;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Joakim
 */
public class ProjectTest {

    Project project1, project2;
    User testUser;

    @Before
    public void before() {
        
        testUser = new User("kalle");
        project1 = new Project("Project1", testUser);
        project2 = new Project("Project2", testUser);
    }

    @Test
    public void testProject() {
        Logger.getAnonymousLogger().log(Level.INFO,
                "Two projects created with attributes: {0} and {1}",
                new Object[]{project1, project2});

        //Two projects with different Id's should not be equal
        assertFalse(project1.equals(project2));
        assertFalse(project2.equals(project1));

        //New Project with same Id as Project1. Though they have different names, Project1 and 3 should now be equal
        Project project3 = new Project(project1.getId(), "project3", testUser);
        assertTrue(project3.equals(project1));
        assertTrue(project1.equals(project3));

        //Change name
        project1.setName("ProjectGroup1");
        assertTrue(project1.getName().equals("ProjectGroup1"));
        
        //Change admin
        User newTestUser = new User("Lisa");
        assertFalse(project1.setAdmin(newTestUser, newTestUser));
        assertTrue(project1.setAdmin(testUser, newTestUser));
    }

    @Test
    public void addRemoveTest() {
        //add and remove elements
        Date date = new Date();
        List<User> testUsers = new ArrayList<User>();

        //user
        project2.addUser(testUser);
        List<User> users = project2.getUsers();
        assertTrue(users.size() == 1);

        project2.deleteUser(testUser);
        assertFalse(users.size() == 1);
        assertTrue(users.isEmpty());

        //article
        project2.createArticle(testUser, "this is an article");
        List<Article> articles = project2.getArticles();
        assertTrue(articles.size() == 1);

        project2.deleteArticle(articles.get(0));
        assertFalse(articles.size() == 1);
        assertTrue(articles.isEmpty());

        //deadlinepost
        project2.createDeadlinePost(testUser, testUser, "this is a DeadlinePost", date, Priority.LOW);
        List<DeadlinePost> deadlinePost = project2.getDeadlinePosts();
        assertTrue(deadlinePost.size() == 1);

        project2.deleteDeadlinePost(deadlinePost.get(0));
        assertFalse(deadlinePost.size() == 1);
        assertTrue(deadlinePost.isEmpty());

        //milestonepost
        project2.createMilestonePost(testUser, testUser, "this is a MilestonePost", date, Priority.MEDIUM, testUsers);
        List<MilestonePost> milestonePosts = project2.getMilestonePosts();
        assertTrue(milestonePosts.size() == 1);

        project2.deleteMilestonePost(milestonePosts.get(0));
        assertFalse(milestonePosts.size() == 1);
        assertTrue(milestonePosts.isEmpty());

        //wallpost
        project2.createWallPost(testUser, "this is a wallpost");
        List<WallPost> wallPosts = project2.getWallPosts();
        assertTrue(wallPosts.size() == 1);

        project2.deleteWallPost(wallPosts.get(0));
        assertFalse(wallPosts.size() == 1);
        assertTrue(wallPosts.isEmpty());
    }
}
