/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * A code draft just to give an idea of how I think about the ProjectAllocator
 * @author gusdavi
 */
public class ProjectAllocator {
    HashMap<Long, List<Long>> getProjectsByUserID;
    HashMap<Long, List<Long>> getUsersByProjectID;

    public void addProject(long userID, String name) {
        Project p = new Project(userID, name);
        long pid = p.getID();
        List<Long> pidsForThisUser = getProjectsByUserID.get(userID);
        if (pidsForThisUser == null) {
            pidsForThisUser = new LinkedList<Long>();
        }
        pidsForThisUser.add(pid);
        getProjectsByUserID.put(userID, pidsForThisUser);

        List<Long> usersInThisProject = getUsersByProjectID.get(pid);
        if (usersInThisProject == null) {
            usersInThisProject = new LinkedList<Long>();
        }
        usersInThisProject.add(userID);
        getUsersByProjectID.put(pid, usersInThisProject);
        
        ProjectRegistry.getInstance().add(p);
    }
    
    public void addCollaborator(long collaboratorId, long projectId){
        
    }
}