/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Project;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Joakim
 */
public class ProjectDAO extends AbstractDAO<Project, Long> {

    public static ProjectDAO newInstance() {
        return new ProjectDAO();
    }

    private ProjectDAO() {
        super(Project.class);
    }
           
    @Override
    public void add(Project project) {
        final Calendar time = new GregorianCalendar();
        project.setDateCreated(time);
        super.add(project);
    }
}

    // Correctly implement these methods as well as tests when needed
    /*
    public List<Project> getByName(String name) {
        List<Project> found = new ArrayList<>();
        for (Project p : getAll()) {
            if (p.getName().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }

    public List<Project> getProjects() {
        List<Project> found = new ArrayList<>();
        for (Project p : getAll()) {
            found.add(p);
        }
        return found;
    }

    public List<Project> getByUser(Person user) {
        List<Project> found = new ArrayList<>();
        for (Project p : getAll()) {
            if (p.getCollaborators().contains(user) || p.getAdmin().equals(user)) {
                found.add(p);
            }
        }
        return found;
    }

    public List<Project> getByAdmin(Person user) {
        List<Project> found = new ArrayList<>();
        for (Project p : getAll()) {
            if (p.getAdmin().equals(user)) {
                found.add(p);
            }
        }
        return found;
    }
*/
