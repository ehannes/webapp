/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
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
        
        testUser = new User("kalle", "kallemail");
        project1 = new Project("Project1", testUser);
        project2 = new Project("Project2", testUser);
    }

    @Ignore
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
        User newTestUser = new User("Lisa", "lisamail");
        try {
            project1.setAdmin(newTestUser, newTestUser);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertFalse(project1.getAdmin()== newTestUser);
        try {
            project1.setAdmin(testUser, newTestUser);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertTrue(project1.getAdmin() == newTestUser);
    }

    @Ignore
    @Test
    public void addRemoveTest() {
        //add and remove elements
        User testUser2 = new User("Lisa", "lisamail");

        //user
        try {
            project2.addCollaborator(testUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        List<User> users = project2.getCollaborators();
        assertFalse(users.size() == 1);

        try {
            project2.addCollaborator(testUser2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        List<User> users2 = project2.getCollaborators();
        assertTrue(users2.size() == 1);

        project2.deleteCollaborator(testUser2);
        assertFalse(users2.size() == 1);
        assertTrue(users2.isEmpty());

        //article
        project2.createArticle(testUser, "this is an article", "articleTitle");
        List<Article> articles = project2.getArticles();
        assertTrue(articles.size() == 1);

        project2.deleteArticle(articles.get(0));
        assertFalse(articles.size() == 1);
        assertTrue(articles.isEmpty());

        //milestonePosts
        project2.createMilestonePost(testUser, "this is a MilestonePost");
        List<TodoPost> milestonePosts = project2.getMilestonePosts();
        assertTrue(milestonePosts.size() == 1);

        project2.deleteMilestonePost(milestonePosts.get(0));
        assertFalse(milestonePosts.size() == 1);
        assertTrue(milestonePosts.isEmpty());
        
        //todoPosts
        project2.createTodoPost(testUser, "this is a todoPost");
        List<TodoPost> todoPosts = project2.getTodoPosts();
        assertTrue(todoPosts.size() == 1);

        project2.deleteTodoPost(todoPosts.get(0));
        assertFalse(todoPosts.size() == 1);
        assertTrue(todoPosts.isEmpty());

        //wallpost
        project2.createWallPost(testUser, "this is a wallpost");
        List<WallPost> wallPosts = project2.getWallPosts();
        assertTrue(wallPosts.size() == 1);

        project2.deleteWallPost(wallPosts.get(0));
        assertFalse(wallPosts.size() == 1);
        assertTrue(wallPosts.isEmpty());
    }
}
