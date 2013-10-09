/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webapp_model;

import com.adde.webapp_model.ProjectPlatformFactory;
import com.adde.webapp_model.ProjectPlatform;
import com.adde.webapp_model.User;
import com.adde.webapp_model.Project;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public class ProjectPlatformTest {
    private ProjectPlatform projectPlatform;
    User benny;
    Project project;
            
    // Total number of owners
    private final static int OWNERS = 5;
    // Collaborators per project
    private final static int COLLABORATORS_PER_PROJECT = 3;
    
    @Before 
    public void setup() {
        projectPlatform = ProjectPlatformFactory.getProjectPlatform(true);
        benny = new User("Benny", "bennyemail");
        project = new Project("Bennys Wiki", benny);
    }

    @Test
    public void getAllUsers() {
        assertTrue(projectPlatform.getUsers().size() == (OWNERS + (OWNERS * COLLABORATORS_PER_PROJECT)));
    }
    
    @Test
    public void getAllProjects() {
        assertTrue(projectPlatform.getProjects().size() == OWNERS);
    }
    
    @Test
    public void oneUserOneProject() {
        try {
        // Create user and check if he's been added to the platform.
        projectPlatform.addUser(benny);
        assertTrue(projectPlatform.getUsers().contains(benny));
        
        // Create project and check if it's been added to the platform.
        projectPlatform.addProject(project);
        assertTrue(projectPlatform.getProjects().contains(project));
        
        // Get a project by a specific user
        assertTrue(projectPlatform.getProjectsByUser(benny).get(0).equals(project));
        
        // Get a user by his name
        assertTrue(projectPlatform.getUserByUserName(benny.getUserName()).equals(benny));
        
        } catch (NullPointerException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    @Test
    public void removeUser() {        
        try {
            projectPlatform.addUser(benny);
            assertTrue(projectPlatform.getUsers().contains(benny));
            
            assertTrue(projectPlatform.removeUser(benny));            
            assertFalse(projectPlatform.getUsers().contains(benny));
            
            // Shouldn't be able to remove a user who's not added to the platform
            assertFalse(projectPlatform.removeUser(new User("Bo", "bomail")));
        } catch (NullPointerException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    @Test
    public void removeProject() {        
        try {
            projectPlatform.addProject(project);
            assertTrue(projectPlatform.getProjects().contains(project));
            
            assertTrue(projectPlatform.removeProject(project));            
            assertFalse(projectPlatform.getProjects().contains(project));
            
            // Shouldn't be able to remove a project that's not added to the platform
            assertFalse(projectPlatform.removeProject(new Project("Bennys project", benny)));
        } catch (NullPointerException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // Check that the static data has been initialized correctly.
    @Test
    public void printInitData() {
     for (User u : projectPlatform.getUsers()) {
            System.out.println("In platform: " + u);
        }
        
        for (Project proj : projectPlatform.getProjects()) {
            System.out.println("In platform: " + proj);
            for(User usr : proj.getCollaborators()) {
                System.out.println("Collaborator in " + proj.toString());
            }
        }
    }
}
