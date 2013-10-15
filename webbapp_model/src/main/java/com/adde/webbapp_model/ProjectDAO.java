/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import com.adde.webbapp_model_util.AbstractDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joakim
 */
public class ProjectDAO extends AbstractDAO<Project, Long> {

    public static ProjectDAO newInstance(String puName) {
        return new ProjectDAO(puName);
    }

    private ProjectDAO(String puName) {
        super(Project.class, puName);
    }

    public List<Project> getByName(String name) {
        List<Project> found = new ArrayList<>();
        for (Project p : getRange(0, getCount())) {
            if (p.getName().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }

    public List<Project> getProjects() {
        List<Project> found = new ArrayList<>();
        for (Project p : getRange(0, getCount())) {
            found.add(p);
        }
        return found;
    }

    public List<Project> getByUser(Person user) {
        List<Project> found = new ArrayList<>();
        for (Project p : getRange(0, getCount())) {
            if (p.getCollaborators().contains(user) || p.getAdmin().equals(user)) {
                found.add(p);
            }
        }
        return found;
    }

    public List<Project> getByAdmin(Person user) {
        List<Project> found = new ArrayList<>();
        for (Project p : getRange(0, getCount())) {
            if (p.getAdmin().equals(user)) {
                found.add(p);
            }
        }
        return found;
    }
}
