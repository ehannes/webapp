package com.adde.webbapp_model;

/**
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public class ProjectPlatformFactory {
    
    private ProjectPlatformFactory() {
    }

    public static ProjectPlatform getProjectPlatform(String puName) {
        return new ProjectPlatform(puName);
    }
}