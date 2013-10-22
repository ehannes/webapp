/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.model;

import com.adde.webbapp.model.dao.ArticleCatalogue;
import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.EMFUtil;
import com.adde.webbapp.model.dao.PersonCatalogue;
import com.adde.webbapp.model.dao.ProjectCatalogue;
import com.adde.webbapp.model.dao.TodoPostCatalogue;
import com.adde.webbapp.model.dao.WallPostCatalogue;
import com.adde.webbapp.model.entity.Article;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.Project;
import com.adde.webbapp.model.entity.TodoPost;
import com.adde.webbapp.model.entity.WallPost;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Joakim
 */
public class ProjectTest {

    ProjectCatalogue projectCatalogue;
    PersonCatalogue personCatalogue;
    TodoPostCatalogue todoPostCatalogue;
    ArticleCatalogue articleCatalogue;
    WallPostCatalogue wallPostCatalogue;
    Project cascadingProject;
    Person testUser;
    Person collaborator;
    Article testArticle;
    TodoPost todoPostTest;
    WallPost wallPostTest;

    public ProjectTest() {
    }

    @BeforeClass
    public static void createEMF() {
        EMFUtil.createEntityManagerFactory();
    }
    
    @AfterClass
    public static void destroyEMF() {
        EMFUtil.destroyEntityManagerFactory();
    }
    
    @Before
    public void before() {

        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        
        projectCatalogue = daoFactory.getProjectCatalogue();
        personCatalogue = daoFactory.getPersonCatalogue();
        articleCatalogue = daoFactory.getArticleCatalogue();
        todoPostCatalogue = daoFactory.getTodoPostCatalogue();
        wallPostCatalogue = daoFactory.getWallPostCatalogue();

        testUser = new Person("kalle", "kallemail", "ellak");
        collaborator = new Person("Colle", "Borator", "elloc");
        cascadingProject = new Project("CascadingProjcect", testUser);
        testArticle = new Article("apa apa apa", "Apor");
        todoPostTest = new TodoPost(testUser, "todoPost");
        wallPostTest = new WallPost(testUser, "wallPost");

        personCatalogue.add(testUser);      

        projectCatalogue.add(cascadingProject);

        articleCatalogue.add(testArticle);
        todoPostCatalogue.add(todoPostTest);
        wallPostCatalogue.add(wallPostTest);

        assertTrue(projectCatalogue.getAll().size() == 1);
        assertTrue(personCatalogue.getAll().size() == 1);
        assertTrue(articleCatalogue.getAll().size() == 1);
        assertTrue(todoPostCatalogue.getAll().size() == 1);
        assertTrue(wallPostCatalogue.getAll().size() == 1);
    }

    @After //Remember to remove all SimpleEditorEntries at end of all tests!
    public void after() {

        for (Project p : projectCatalogue.getAll()) {
            projectCatalogue.remove(p.getId());
        }
        personCatalogue.remove(testUser.getId());
        personCatalogue.remove(collaborator.getId());
        
        //check if everything is gone
        assertTrue(projectCatalogue.getAll().isEmpty());
        assertTrue(personCatalogue.getAll().isEmpty());
        assertTrue(articleCatalogue.getAll().isEmpty());
        assertTrue(todoPostCatalogue.getAll().isEmpty());
        assertTrue(wallPostCatalogue.getAll().isEmpty());
    }

    @Test
    public void testCascading() {

        personCatalogue.add(collaborator);
         
        //add content to project
        cascadingProject = projectCatalogue.find(cascadingProject.getId());
        cascadingProject.getArticles().add(testArticle);
        cascadingProject.getCollaborators().add(collaborator);
        cascadingProject.getTodoPosts().add(todoPostTest);
        cascadingProject.getWallPosts().add(wallPostTest);
        projectCatalogue.update(cascadingProject);

        assertTrue(articleCatalogue.find(testArticle.getId()).equals(testArticle));
        assertTrue(projectCatalogue.find(cascadingProject.getId()).getCollaborators().size() == 1 );
        assertTrue(todoPostCatalogue.find(todoPostTest.getId()).equals(todoPostTest));
        assertTrue(wallPostCatalogue.find(wallPostTest.getId()).equals(wallPostTest));

    }
}
