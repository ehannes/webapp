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
    
    private List<Person> users;
    private List<Project> projects;
    
    public ProjectPlatform() {
        users = new ArrayList<>();
        projects = new ArrayList<>();
        Logger.getAnonymousLogger().log(Level.INFO, "ProjectPlatform alive {0}", this.hashCode());
    }
    
    public void addUser(Person user) throws Exception {
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
    public boolean removeUser(Person user) {
        return users.remove(user);
    }
    
    public boolean removeProject(Project project) {
        return projects.remove(project);
    }
    
    public List<Person> getUsers() {
        return users;
    }
    
    public List<Project> getProjects() {
        return projects;
    }
    
    public List<Project> getProjectsByUser(Person user) {
        List<Project> userProjects = new ArrayList<>();
        for(Project p : projects) {
            if(p.getCollaborators().contains(user) || user.equals(p.getAdmin())) {
                userProjects.add(p);
            }
        }
        return userProjects;
    }
    
    public Person getUserByUserName(String username) {
        for(Person u : users) {
            if(u.getUserName().equals(username)) {
                return u;
            }
        }
        return null;
    }
    
    public Person getUserByFirstName(String firstname) {
        for(Person u : users) {
            if(u.getFirstName().equals(firstname)) {
                return u;
            }
        }
        return null;
    }
    
    public Person getUserByLastName(String lastname) {
        for(Person u : users) {
            if(u.getLastName().equals(lastname)) {
                return u;
            }
        }
        return null;
    }
    
    public Person getUserByEmail(String email) {
        for(Person u : users) {
            if(u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
}