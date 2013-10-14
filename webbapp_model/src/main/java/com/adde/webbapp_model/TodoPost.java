package com.adde.webbapp_model;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class TodoPost extends Post {
    private GregorianCalendar deadline; //may be null
    public static enum Priority{LOW, MEDIUM, HIGH};
    private Priority currentPrio;
    private List<Person> assignedTo;

    public TodoPost(Person author, String msg){
        this(author, msg, null);
    }
    
    public TodoPost(Person author, String msg, GregorianCalendar deadline){
        super(author, msg);
        assignedTo = new LinkedList<Person>();
        this.deadline = deadline;
    }

    public void setDeadline(GregorianCalendar deadline) {
        this.deadline = deadline;
    }

    public void setPriority(Priority p){
        currentPrio = p;
    }

    public void clearAssignedTo(){
        assignedTo = new LinkedList<Person>();
    }

    public void assignTo(Person u){
        if(!assignedTo.contains(u)){
            assignedTo.add(u);
        }
    }

    //Only accepts non-null lists, also no duplicates!
    public boolean assignTo(List<Person> assignTo){
        if(assignTo == null){
            return false;
        }
        for(Person u : assignTo){
            if(!assignedTo.contains(u)){
                assignedTo.add(u);
            }
        }
        return true;
    }
    
    public void unassign(Person u){
        assignedTo.remove(u);
    }

    public GregorianCalendar getDeadline() {
        return deadline;
    }
    
    public String getStringDeadline() {
        return "Deadline: " + deadline.get(GregorianCalendar.DAY_OF_MONTH)
                + "/" + (deadline.get(GregorianCalendar.MONTH)+1)
                + " " + deadline.get(GregorianCalendar.YEAR);
    }
    
    public List<Person> getAssignedTo(){
        return assignedTo;
    }
    
    public Priority getPriority() {
        return currentPrio;
    }
    
    @Override
    public String toString(){
        return "DeadlinePost{id=" + getId() + ", author=" + getAuthor() + ", msg=" + getMsg()
                + ", deadline=" + getStringDeadline() + ", currentPrio=" + currentPrio
                + ", dateCreated=" + getStringDateCreated() + ", dateModified="
                + getStringDateModified() + ", assignedTo=" + assignedTo + '}';
    }
}