package com.adde.webbapp.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Utility class for the creation of the EntityManagerFactory and EntityManagers.
 * The createEntityManagerFactory method should  be called once in the beginning
 * of application startup and destroyEntityManager method needs to be called at
 * shutdown.
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