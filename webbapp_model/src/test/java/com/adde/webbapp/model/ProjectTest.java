/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.model;

import com.adde.webbapp.model.dao.ArticleCatalogue;
import com.adde.webbapp.model.dao.DAOFactory;
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
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Joakim
 */
public class ProjectTest {

    ProjectCatalogue projectDAO;
    PersonCatalogue personDAO;
    TodoPostCatalogue todoPostDAO;
    ArticleCatalogue articleDAO;
    WallPostCatalogue wallPostDAO;
    Project cascadingProject;
    Person testUser;
    Person collaborator;
    Article testArticle;
    TodoPost todoPostTest;
    WallPost wallPostTest;

    public ProjectTest() {
    }

    @Before
    public void before() {

        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        projectDAO = daoFactory.getProjectCatalogue();
        personDAO = daoFactory.getPersonCatalogue();
        articleDAO = daoFactory.getArticleCatalogue();
        todoPostDAO = daoFactory.getTodoPostCatalogue();
        wallPostDAO = daoFactory.getWallPostCatalogue();

        testUser = new Person("kalle", "kallemail", "ellak");
        collaborator = new Person("Colle", "Borator", "elloc");
        cascadingProject = new Project("CascadingProjcect", testUser);
        testArticle = new Article("apa apa apa", "Apor");
        todoPostTest = new TodoPost(testUser, "todoPost");
        wallPostTest = new WallPost(testUser, "wallPost");

        personDAO.add(testUser);      

        projectDAO.add(cascadingProject);

        articleDAO.add(testArticle);
        todoPostDAO.add(todoPostTest);
        wallPostDAO.add(wallPostTest);

        assertTrue(projectDAO.getAll().size() == 1);
        assertTrue(personDAO.getAll().size() == 1);
        assertTrue(articleDAO.getAll().size() == 1);
        assertTrue(todoPostDAO.getAll().size() == 1);
        assertTrue(wallPostDAO.getAll().size() == 1);
    }

    @After //Remember to remove all SimpleEditorEntries at end of all tests!
    public void after() {

        for (Project p : projectDAO.getAll()) {
            projectDAO.remove(p.getId());
        }
        personDAO.remove(testUser.getId());
        personDAO.remove(collaborator.getId());

        //check if everything is gone
        assertTrue(projectDAO.getAll().isEmpty());
        assertTrue(personDAO.getAll().isEmpty());
        assertTrue(articleDAO.getAll().isEmpty());
        assertTrue(todoPostDAO.getAll().isEmpty());
        assertTrue(wallPostDAO.getAll().isEmpty());
    }

    @Test
    public void testCascading() {

        personDAO.add(collaborator);
         
        //add content to project
        cascadingProject = projectDAO.find(cascadingProject.getId());
        cascadingProject.getArticles().add(testArticle);
        cascadingProject.getCollaborators().add(collaborator);
        cascadingProject.getTodoPosts().add(todoPostTest);
        cascadingProject.getWallPosts().add(wallPostTest);
        projectDAO.update(cascadingProject);

        assertTrue(articleDAO.find(testArticle.getId()).equals(testArticle));
        assertTrue(projectDAO.find(cascadingProject.getId()).getCollaborators().size() == 1 );
        assertTrue(todoPostDAO.find(todoPostTest.getId()).equals(todoPostTest));
        assertTrue(wallPostDAO.find(wallPostTest.getId()).equals(wallPostTest));

    }
}
