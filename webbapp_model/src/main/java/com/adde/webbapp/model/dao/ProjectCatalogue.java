package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Project;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Joakim
 */
public class ProjectCatalogue extends AbstractDAO<Project, Long> {

    public static ProjectCatalogue newInstance() {
        return new ProjectCatalogue();
    }

    private ProjectCatalogue() {
        super(Project.class);
    }
           
    @Override
    public void add(Project project) {
        final Calendar time = new GregorianCalendar();
        project.setDateCreated(time);
        super.add(project);
    }
}