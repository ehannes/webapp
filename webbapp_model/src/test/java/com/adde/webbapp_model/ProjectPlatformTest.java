/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import com.adde.webbapp_model_dao.ProjectDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public class ProjectPlatformTest {

    static ProjectPlatform projectPlatform;
    final static String PU = "webapp_pu";
    private Person testPerson;
    private Person collaborator;
    private Project testProject;

    @Before
    public void setup() {
        projectPlatform = ProjectPlatformFactory.getProjectPlatform(PU);
        testPerson = new Person("Test Testsson", "test@test.com");
        collaborator = new Person("Colla borator", "colla@test.com");
        testProject = new Project("testProject", testPerson);
    }

    @Test
    public void addUser() {
        projectPlatform.addUser(testPerson);
        projectPlatform.addProject(testProject);
        try {
            testProject.addCollaborator(collaborator);
        } catch (Exception ex) {
            Logger.getLogger(ProjectPlatformTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ProjectDAO.newInstance().update(testProject);
    }
    //ArticleDAO ad = new ArticleDAO(ProjectPlatform.PU);
    //ad.add(article);
    //ad.add(article);
//    // Depends on the static data initialized in another class (see boolean above). Bad?
//    @Ignore
//    @Test
//    public void getAllUsers() {
//        assertTrue(projectPlatform.getUsers().size() == (OWNERS + (OWNERS * COLLABORATORS_PER_PROJECT)));
//    }
//
//    // Depends on the static data initialized in another class (see boolean above). Bad?
//    @Ignore
//    @Test
//    public void getAllProjects() {
//        System.out.println("SIZE: " + projectPlatform.getProjects().size());
//        assertTrue(projectPlatform.getProjects().size() == OWNERS);
//    }
//
//    @Ignore
//    @Test
//    public void getUser() {
//        try {
//            projectPlatform.addUser(benny);
//        } catch (NullPointerException e) {
//            System.out.println(e.toString());
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//
//        // Get user by name
//        assertTrue(projectPlatform.getUserByUserName(benny.getUserName()).equals(benny));
//
//        // Get user by first name
//        assertTrue(projectPlatform.getUserByFirstName(benny.getFirstName()).equals(benny));
//
//        // Get user by last name
//        assertTrue(projectPlatform.getUserByLastName(benny.getLastName()).equals(benny));
//
//        // Get user by email
//        assertTrue(projectPlatform.getUserByEmail(benny.getEmail()).equals(benny));
//    }
//    
//    // Test for testing the platform from the root class to the bottom classes
//    @Ignore
//    @Test
//    public void userArticleWallPostTodoPost() {
//        try {
//            projectPlatform.addUser(benny);
//            projectPlatform.addProject(project);
//        } catch (NullPointerException e) {
//            System.out.println(e.toString());
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        
//        // Create article and see that we can retrieve the content and title from the project platform
//        String title = "Bennys Article";
//        String content = "content...";
//        project.createArticle(benny, content, title);
//         
//        Article addedArticle = projectPlatform.getProjectsByUser(benny).get(0).getArticles().get(0);
//        
//        assertTrue(addedArticle.getTitle().equals(title));
//        assertTrue(addedArticle.getContent().equals(content));
//        
//        String updatedContent = "new changed content...";
//        String updatedTitle = "new changed title";
//        addedArticle.update(benny, updatedContent, updatedTitle);
//        
//        assertTrue(addedArticle.getContent().equals(updatedContent));
//        assertTrue(addedArticle.getTitle().equals(updatedTitle));
//        
//        // Create WallPost and see that we can retrieve the content from the project platform
//        project.createWallPost(benny, content);
//        String receivedContent = projectPlatform.getProjectsByUser(benny).get(0).getWallPosts().get(0).getMsg();
//        assertTrue(receivedContent.equals(content));
//        
//        // Create TodoPost and see that we can retrieve the content from the project platform
//        project.createTodoPost(benny, content);
//        assertTrue(projectPlatform.getProjectsByUser(benny).get(0).getTodoPosts().get(0).getMsg().equals(content));
//    }
//
//    @Ignore
//    @Test
//    public void oneUserOneProject() {
//        try {
//            // Add the user and his project to the platform
//            projectPlatform.addUser(benny);
//            projectPlatform.addProject(project);
//        } catch (NullPointerException e) {
//            System.out.println(e.toString());
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        // Check if the added user has been added to the platform
//        assertTrue(projectPlatform.getUsers().contains(benny));
//
//        // Check if the project has been added to the platform
//        assertTrue(projectPlatform.getProjects().contains(project));
//
//        // Get the users added project 
//        assertTrue(projectPlatform.getProjectsByUser(benny).get(0).equals(project));
//    }
//    
//    @Ignore
//    @Test
//    public void oneUserMultipleProjects() {
//        List<Project> bennysProjects = new ArrayList<>();
//        List<Person> bennysCollaborators = new ArrayList<>();
//        try {
//            projectPlatform.addUser(benny);
//            projectPlatform.addProject(project);
//            
//            Project secondProject = new Project("Bennys second project", benny);
//            projectPlatform.addProject(secondProject);
//                
//            bennysProjects.add(project);
//            bennysProjects.add(secondProject);
//            for (int i = 0; i < COLLABORATORS_PER_PROJECT; i++) {
//                Person collaborator = new Person("Collaborator " + i, 
//                        "collaborator " + i + "@collaborators.com");
//                bennysCollaborators.add(collaborator);
//                projectPlatform.addUser(collaborator);
//                project.addCollaborator(collaborator);
//                secondProject.addCollaborator(collaborator);
//            }
//        } catch (NullPointerException e) {
//            System.out.println(e.toString());
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        // Check if the user has the correct amount of projects
//        assertTrue(bennysProjects.size() == projectPlatform.getProjectsByUser(benny).size());
//        
//        // Check if all the users projects and collaborators are equal to the ones in the platform
//        for(int i = 0; i < bennysProjects.size(); i++) {
//            assertTrue(bennysProjects.get(i).equals(projectPlatform.getProjectsByUser(benny).get(i)));
//            assertTrue(bennysCollaborators.equals(bennysProjects.get(i).getCollaborators()));
//        }
//    }
//
//    @Ignore
//    @Test
//    public void removeUser() {
//        try {
//            projectPlatform.addUser(benny);
//
//        } catch (NullPointerException e) {
//            System.out.println(e.toString());
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        assertTrue(projectPlatform.getUsers().contains(benny));
//
//        assertTrue(projectPlatform.removeUser(benny));
//        assertFalse(projectPlatform.getUsers().contains(benny));
//
//        // Shouldn't be able to remove a user who's not added to the platform
//        assertFalse(projectPlatform.removeUser(new Person("Bo", "bomail")));
//    }
//
//    @Ignore
//    @Test
//    public void removeProject() {
//        try {
//            projectPlatform.addProject(project);
//        } catch (NullPointerException e) {
//            System.out.println(e.toString());
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//        assertTrue(projectPlatform.getProjects().contains(project));
//
//        assertTrue(projectPlatform.removeProject(project));
//        assertFalse(projectPlatform.getProjects().contains(project));
//
//        // Shouldn't be able to remove a project that's not added to the platform
//        assertFalse(projectPlatform.removeProject(new Project("Bennys project", benny)));
//    }
//
//    // Just for printing the test data
//    @Ignore
//    @Test
//    public void printInitData() {
//        for (Person u : projectPlatform.getUsers()) {
//            System.out.println("In platform: " + u);
//        }
//
//        for (Project proj : projectPlatform.getProjects()) {
//            System.out.println("In platform: " + proj);
//            for (Person usr : proj.getCollaborators()) {
//                System.out.println("Collaborator in " + proj.toString());
//            }
//        }
//    }
}