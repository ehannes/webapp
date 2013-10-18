package com.adde.webbapp.model.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton root class for the model holding all users and projects.
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public class DAOFactory {

    private final ArticleCatalogue articleCatalogue;
    private final PostCatalogue postCatalogue;
    private final PersonCatalogue personCatalogue;
    private final ProjectCatalogue projectCatalogue;
    private final ArticleEditCatalogue articleEditCatalogue;
    private final TodoPostCatalogue todoPostCatalogue;
    private final WallPostCatalogue wallPostCatalogue;

    private DAOFactory() {
        articleCatalogue = ArticleCatalogue.newInstance();
        postCatalogue = PostCatalogue.newInstance();
        personCatalogue = PersonCatalogue.newInstance();
        projectCatalogue = ProjectCatalogue.newInstance();
        articleEditCatalogue = ArticleEditCatalogue.newInstance();
        todoPostCatalogue = TodoPostCatalogue.newInstance();
        wallPostCatalogue = WallPostCatalogue.newInstance();
        Logger.getAnonymousLogger().log(Level.INFO, "DAOFactory alive {0}", this.hashCode());
    }
    
    public static DAOFactory getDAOFactory() {
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory;
    }

    public ArticleCatalogue getArticleCatalogue() {
        return articleCatalogue;
    }

    public PostCatalogue getPostCatalogue() {
        return postCatalogue;
    }

    public PersonCatalogue getPersonCatalogue() {
        return personCatalogue;
    }

    public ProjectCatalogue getProjectCatalogue() {
        return projectCatalogue;
    }

    public ArticleEditCatalogue getArticleEditCatalogue() {
        return articleEditCatalogue;
    }

    public TodoPostCatalogue getTodoPostCatalogue() {
        return todoPostCatalogue;
    }

    public WallPostCatalogue getWallPostCatalogue() {
        return wallPostCatalogue;
    }
}