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
    
    public void addUser(User user) throws Exception {
        if (user == null) {
            throw new NullPointerException("Nulls not allowed");
        }
        if(users.contains(user)) {
            throw new Exception("Duplicate users not allowed, user id: " + user.getId());
        }
        users.add(user);
    }
    
    public void addProject(Project project) throws Exception {
        if (project == null) {
            throw new IllegalArgumentException("Nulls not allowed");
        }
        if(projects.contains(project)) {
            throw new Exception("Duplicate projects not allowed, project id: " + project.getId());
        }
        projects.add(project);
    }
    
    // Possibly throw exception instead of returning boolean
    public boolean removeUser(User user) {
        return users.remove(user);
    }
    
    public boolean removeProject(Project project) {
        return projects.remove(project);
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    public List<Project> getProjects() {
        return projects;
    }
    
    public List<Project> getProjectsByUser(User user) {
        List<Project> userProjects = new ArrayList<Project>();
        for(Project p : projects) {
            if(p.getCollaborators().contains(user) || user.equals(p.getAdmin())) {
                userProjects.add(p);
            }
        }
        return userProjects;
    }
    
    public User getUserByName(String name) {
        for(User u : users) {
            if(u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }
}
