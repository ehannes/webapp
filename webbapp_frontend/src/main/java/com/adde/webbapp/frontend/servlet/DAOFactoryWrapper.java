/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.frontend.servlet;

import com.adde.webbapp.model.dao.ArticleCatalogue;
import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PersonCatalogue;
import com.adde.webbapp.model.dao.PostCatalogue;
import com.adde.webbapp.model.dao.ProjectCatalogue;
import com.adde.webbapp.model.dao.ArticleEditCatalogue;
import com.adde.webbapp.model.dao.TodoPostCatalogue;
import com.adde.webbapp.model.dao.WallPostCatalogue;

/**
 * This is a wrapper to make the shop a singleton Could have used CDI (?) but
 * too much for now, introduced later
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public enum DAOFactoryWrapper {

    INSTANCE;
    private final DAOFactory d;

    private DAOFactoryWrapper() {
        d = DAOFactory.getDAOFactory();
    }

    public PersonCatalogue getPersonDAO() {
        return d.getPersonCatalogue();
    }

    public ProjectCatalogue getProjectDAO() {
        return d.getProjectCatalogue();
    }

    public PostCatalogue getPostDAO() {
        return d.getPostCatalogue();
    }

    public TodoPostCatalogue getTodoPostDAO() {
        return d.getTodoPostCatalogue();
    }

    public ArticleCatalogue getArticleDAO() {
        return d.getArticleCatalogue();
    }

    public WallPostCatalogue getWallPostDAO() {
        return d.getWallPostCatalogue();
    }
    public ArticleEditCatalogue getSimpleEditorEntryDAO() {
        return d.getArticleEditCatalogue();
    }
}