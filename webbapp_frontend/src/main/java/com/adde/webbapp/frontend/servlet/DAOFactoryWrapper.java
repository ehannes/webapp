/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.frontend.servlet;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PersonDAO;

/**
 * This is a wrapper to make the shop a singleton
 * Could have used CDI (?) but too much for now, introduced later
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public enum DAOFactoryWrapper {
    INSTANCE;
    
    private final DAOFactory d;
    
    private DAOFactoryWrapper(){
       d = DAOFactory.getDAOFactory();
    }
    
    public PersonDAO getPersonDAO() {
        return d.getPersonDAO();
    }
}