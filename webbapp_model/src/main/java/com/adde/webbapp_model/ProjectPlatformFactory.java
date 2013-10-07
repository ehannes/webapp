package com.adde.webbapp_model;

/**
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public class ProjectPlatformFactory {
    private final static int NO_OF_USERS = 5;
    private final static int NO_OF_COLLABORATORS = 3;
    
    private ProjectPlatformFactory() {
    }

    public static ProjectPlatform getProjectPlatform(boolean initTestData) {
        ProjectPlatform p = new ProjectPlatform();
        if (initTestData) {
            initTestData(p);
        }
        return p;
    }
    
    private static void initTestData(ProjectPlatform p) {
        try {
            for(int i = 0; i < NO_OF_USERS; i++) {
                User owner = new User("Owner " + i);
                p.addUser(owner);
                Project project = new Project("Test project " + i, owner);
                for (int k = 0; k < NO_OF_COLLABORATORS; k++) {
                    User collaborator = new User("Collaborator " + i);
                    project.addCollaborator(collaborator);
                }
                p.addProject(project);
            }
        } catch (NullPointerException e) {
            System.out.println("Fail in initTestData");
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println("Fail in initTestData");
            System.out.println(e.toString());
        }
    }
}
