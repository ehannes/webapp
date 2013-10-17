/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.frontend.servlet;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author hajo
 */
@WebListener()
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        Logger.getAnonymousLogger().log(Level.INFO, "Session created");
        Logger.getAnonymousLogger().log(Level.INFO, se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Logger.getAnonymousLogger().log(Level.INFO, "Session destroyed");
        Logger.getAnonymousLogger().log(Level.INFO, se.getSession().getId()); 
    }
}
