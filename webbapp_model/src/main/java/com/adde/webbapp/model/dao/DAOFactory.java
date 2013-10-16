package com.adde.webbapp.model.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton root class for the model holding all users and projects.
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public class DAOFactory {

    private final ArticleDAO articleDAO;
    private final CommentDAO commentDAO;
    private final PersonDAO personDAO;
    private final ProjectDAO projectDAO;
    private final SimpleEditorEntryDAO simpleEditorEntryDAO;
    private final TodoPostDAO todoPostDAO;
    private final WallPostDAO wallPostDAO;
    private static final String PU = "webapp_pu";

    public DAOFactory(String puName) {
        articleDAO = ArticleDAO.newInstance();
        commentDAO = CommentDAO.newInstance();
        personDAO = PersonDAO.newInstance();
        projectDAO = ProjectDAO.newInstance();
        simpleEditorEntryDAO = SimpleEditorEntryDAO.newInstance();
        todoPostDAO = TodoPostDAO.newInstance();
        wallPostDAO = WallPostDAO.newInstance();
        Logger.getAnonymousLogger().log(Level.INFO, "ProjectPlatform alive {0}", this.hashCode());
    }

    public static String getPU() {
        return PU;
    }

    public ArticleDAO getArticleDAO() {
        return articleDAO;
    }

    public CommentDAO getCommentDAO() {
        return commentDAO;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public ProjectDAO getProjectDAO() {
        return projectDAO;
    }

    public SimpleEditorEntryDAO getSimpleEditorEntryDAO() {
        return simpleEditorEntryDAO;
    }

    public TodoPostDAO getTodoPostDAO() {
        return todoPostDAO;
    }

    public WallPostDAO getWallPostDAO() {
        return wallPostDAO;
    }
}