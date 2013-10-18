package com.adde.webbapp.model.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton root class for the model holding all users and projects.
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public class DAOFactory {

    private final ArticleCatalogue articleDAO;
    private final PostCatalogue postDAO;
    private final PersonCatalogue personDAO;
    private final ProjectCatalogue projectDAO;
    private final ArticleEditCatalogue simpleEditorEntryDAO;
    private final TodoPostCatalogue todoPostDAO;
    private final WallPostCatalogue wallPostDAO;

    private DAOFactory() {
        articleDAO = ArticleCatalogue.newInstance();
        postDAO = PostCatalogue.newInstance();
        personDAO = PersonCatalogue.newInstance();
        projectDAO = ProjectCatalogue.newInstance();
        simpleEditorEntryDAO = ArticleEditCatalogue.newInstance();
        todoPostDAO = TodoPostCatalogue.newInstance();
        wallPostDAO = WallPostCatalogue.newInstance();
        Logger.getAnonymousLogger().log(Level.INFO, "DAOFactory alive {0}", this.hashCode());
    }
    
    public static DAOFactory getDAOFactory() {
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory;
    }

    public ArticleCatalogue getArticleDAO() {
        return articleDAO;
    }

    public PostCatalogue getPostDAO() {
        return postDAO;
    }

    public PersonCatalogue getPersonDAO() {
        return personDAO;
    }

    public ProjectCatalogue getProjectDAO() {
        return projectDAO;
    }

    public ArticleEditCatalogue getSimpleEditorEntryDAO() {
        return simpleEditorEntryDAO;
    }

    public TodoPostCatalogue getTodoPostDAO() {
        return todoPostDAO;
    }

    public WallPostCatalogue getWallPostDAO() {
        return wallPostDAO;
    }
}