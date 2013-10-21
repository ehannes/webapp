/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public class EMFUtil {
    private static EntityManagerFactory emf;
    
    public static void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("webapp_pu");
    }
    
    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return emf.createEntityManager();
    }
    
    public static void destroyEntityManagerFactory() {
        emf.close();
    }
}