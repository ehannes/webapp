package com.adde.webbapp_model;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton root class for the model holding all users and projects.
 * 
 * @author Eric Ahlberg (aheric@student.chalmers.se)
 */

public class ProjectPlatform {
    
    private List<User> users;
    private List<Project> projects;
    
    public ProjectPlatform() {
        users = new ArrayList<User>();
        projects = new ArrayList<Project>();
        Logger.getAnonymousLogger().log(Level.INFO, "ProjectPlatform alive {0}", this.hashCode());
    }
    
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Nulls not allowed");
        }
        users.add(user);
    }
    
    public void addProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Nulls not allowed");
        }
        projects.add(project);
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    public List<Project> getProjects() {
        return projects;
    }
}
