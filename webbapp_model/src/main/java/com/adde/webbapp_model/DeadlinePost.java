package com.adde.webbapp_model;

import java.util.Date;

public class DeadlinePost extends Post {
    private Date deadline;
    private User responsibleUser;
    private int priority;
    public static final int PRIORITY_MIN = 1;
    public static final int PRIORITY_MAX = 5;
    
    public DeadlinePost(User author, User responsibleUser, String msg,
            Date deadline, int priority){
        super(author, msg);
        this.responsibleUser = responsibleUser;
        this.deadline = deadline;
        privSetPriority(priority);
    }
    
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public void setPriority(int priority){
        privSetPriority(priority);
    }
    
    //to reduce code duplication
    private void privSetPriority(int priority) {
        if(priority < PRIORITY_MIN){
            this.priority = PRIORITY_MIN;
        } else if(priority > PRIORITY_MAX){
            this.priority = PRIORITY_MAX;
        } else{
            this.priority = priority;
        }
    }

    public Date getDeadline() {
        return deadline;
    }

    public User getResponsibleUser() {
        return responsibleUser;
    }

    public int getPriority() {
        return priority;
    }
}
