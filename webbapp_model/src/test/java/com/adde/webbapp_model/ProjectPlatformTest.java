/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public class ProjectPlatformTest {
    private ProjectPlatform projectPlatform;
    
    @Before 
    public void setup() {
        projectPlatform = ProjectPlatformFactory.getProjectPlatform(true);
    }

    // TODO: Implement usage testcases
    //@Test
    public void testProjectPlatform() {
        ProjectPlatform p = new ProjectPlatform();
    }
    
    // To check that the static data has been initialized correctly.
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
