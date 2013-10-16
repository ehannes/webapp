/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_frontend.servlet;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author aheric
 */
@WebListener()
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Logger.getAnonymousLogger().log(Level.INFO, "Putting Project Platform in application scope");
        sce.getServletContext().setAttribute("PROJECT_PLATFORM", ProjectPlatformWrapper.INSTANCE);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    
    }
}

