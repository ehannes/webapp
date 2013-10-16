/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_frontend_servlet;


import com.adde.webbapp_model.ProjectPlatform;
import com.adde.webbapp_model.ProjectPlatformFactory;

/**
 * This is a wrapper to make the shop a singleton
 * Could have used CDI (?) but too much for now, introduced later
 * @author hajo
 */
public enum ProjectPlatformWrapper {
    INSTANCE;
    
    private final ProjectPlatform p;
    
    private ProjectPlatformWrapper(){
       p = ProjectPlatformFactory.getProjectPlatform(ProjectPlatform.PU);
    }
    public ProjectPlatform getProjectPlatform(){
        return p;
    }
   
}
