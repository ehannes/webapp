package com.adde.webbapp_model;

/**
 *
 * @author hajo, modified by Eric Ahlberg (eahlberg@gmail.com)
 */
public class ProjectPlatformFactory {
    
    private ProjectPlatformFactory() {
    }

    // If initTestData there will be some data to use
    public static ProjectPlatform getProjectPlatform(boolean initTestData) {
        ProjectPlatform p = new ProjectPlatform();
        if (initTestData) {
            initTestData(p);
        }
        return p;
    }
    
    
    private static void initTestData(ProjectPlatform p) {
        // TODO: add test data
    }
}
