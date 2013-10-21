/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.frontend.servlet;

import com.adde.webbapp.model.dao.EMFUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
@WebListener()
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Logger.getAnonymousLogger().log(Level.INFO, "Putting DAOFactory in application scope");
        sce.getServletContext().setAttribute("DAOFACTORY", DAOFactoryWrapper.INSTANCE);
        
        EMFUtil.createEntityManagerFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EMFUtil.destroyEntityManagerFactory();
    }
}