/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.frontend.servlet;

import com.adde.webbapp.model.dao.ArticleDAO;
import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PersonDAO;
import com.adde.webbapp.model.dao.PostDAO;
import com.adde.webbapp.model.dao.ProjectDAO;
import com.adde.webbapp.model.dao.SimpleEditorEntryDAO;
import com.adde.webbapp.model.dao.TodoPostDAO;
import com.adde.webbapp.model.dao.WallPostDAO;

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

    public PersonDAO getPersonDAO() {
        return d.getPersonDAO();
    }

    public ProjectDAO getProjectDAO() {
        return d.getProjectDAO();
    }

    public PostDAO getPostDAO() {
        return d.getPostDAO();
    }

    public TodoPostDAO getTodoPostDAO() {
        return d.getTodoPostDAO();
    }

    public ArticleDAO getArticleDAO() {
        return d.getArticleDAO();
    }

    public WallPostDAO getWallPostDAO() {
        return d.getWallPostDAO();
    }
    public SimpleEditorEntryDAO getSimpleEditorEntryDAO() {
        return d.getSimpleEditorEntryDAO();
    }
}