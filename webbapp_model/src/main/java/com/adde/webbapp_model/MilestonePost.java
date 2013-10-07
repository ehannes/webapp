package com.adde.webbapp_model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MilestonePost extends DeadlinePost {
    private List<User> assignedTo;

    public MilestonePost(User author, User responsibleUser, String msg,
            Date deadline, priority currentPriority, List<User> assignedTo){
        super(author, responsibleUser, msg, deadline, currentPriority);
        this.assignedTo = assignedTo;
    }

    public List<User> getAssignedTo(){
        return assignedTo;
    }
    
    //Only accepts non-null lists, also no duplicates!
    public boolean setAssignedTo(List<User> newAssignedTo){
        if(newAssignedTo == null){
            return false;
        }
        assignedTo = new LinkedList<User>();
        for(User u : newAssignedTo){
            if(!assignedTo.contains(u)){
                assignedTo.add(u);
            }
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "DeadlinePost{author=" + getAuthor() + ", responsibleUser="
                + getResponsibleUser() + ", msg=" + getMsg() + ", deadline="
                + getDeadline() + ", currentPriority=" + getPriority() + ", dateCreated="
                + getDateCreated() + ", dateModified=" + getDateModified()
                + "assignedTo=" + assignedTo + '}';
    }
}