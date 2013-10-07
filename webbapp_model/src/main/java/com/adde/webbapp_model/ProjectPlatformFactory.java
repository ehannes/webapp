package com.adde.webbapp_model;

/**
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public class ProjectPlatformFactory {
    
    private ProjectPlatformFactory() {
    }

    // If initTestData there will be some data to use
    public static ProjectPlatform getProjectPlatform(boolean initTestData) {
        ProjectPlatform p = new ProjectPlatform();
        if (initTestData) {
            initTestData(p);
        }
        return p;
    }
    
    // Fix exception handling
    private static void initTestData(ProjectPlatform p) {
        // TODO: add test data
        
        //p.addUser(new User("Bengt"));
        //p.addUser(new User("Eva"));
        
        // TODO: look into connection between User and Projects, add empty constructor in Project.
        
        /*
         p.addProject(new Project("Evas web application"));
         p.addProject(new Project("Bengts blog"));
         */
    }
}
