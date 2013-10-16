package com.adde.webbapp_model;

import com.adde.webbapp_model_dao.ProjectDAO;
import com.adde.webbapp_model_dao.PersonDAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton root class for the model holding all users and projects.
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public class ProjectPlatform {

    private final ProjectDAO projectDAO;
    private final PersonDAO personDAO;
    public final static String PU = "webapp_pu";

    public ProjectPlatform(String puName) {
        projectDAO = ProjectDAO.newInstance();
        personDAO = PersonDAO.newInstance();
        Logger.getAnonymousLogger().log(Level.INFO, "ProjectPlatform alive {0}", this.hashCode());
    }
    
    public void addProject(Project project) {
        projectDAO.add(project);
    }

    public void removeProject(Project project) {
        projectDAO.remove(project.getId());
    }

    public List<Project> getProjects() {
        return projectDAO.getProjects();
    }

    public List<Project> getProjectsByUser(Person user) {
        return projectDAO.getByUser(user);
    }

    public void addUser(Person user) {
        personDAO.add(user);
    }

    public void removeUser(Person user) {
        personDAO.remove(user.getId());
    }

    public List<Person> getUsers() {
        return personDAO.getUsers();
    }

    public List<Person> getUserByUserName(String username) {
        return personDAO.getByUserName(username);
    }

    public List<Person> getUserByFirstName(String firstname) {
        return personDAO.getByFirstName(firstname);
    }

    public List<Person> getUserByLastName(String lastname) {
        return personDAO.getByLastName(lastname);
    }

    public List<Person> getUserByEmail(String email) {
        return personDAO.getByEmail(email);
    }
}