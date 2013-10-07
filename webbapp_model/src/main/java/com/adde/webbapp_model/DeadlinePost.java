package com.adde.webbapp_model;

import java.util.Date;

public class DeadlinePost extends Post {
    private Date deadline;
    private User responsibleUser;
    public static enum priority{LOW, MEDIUM, HIGH};
    private priority currentPrio;
    
    public DeadlinePost(User author, User responsibleUser, String msg,
            Date deadline, priority p){
        super(author, msg);
        this.responsibleUser = responsibleUser;
        this.deadline = deadline;
        privSetPriority(p);
    }
    
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public void setPriority(priority p){
        privSetPriority(p);
    }
    
    //to reduce code duplication
    private void privSetPriority(priority p) {
        currentPrio = p;
    }

    public Date getDeadline() {
        return deadline;
    }

    public User getResponsibleUser() {
        return responsibleUser;
    }

    public priority getPriority() {
        return currentPrio;
    }
    
    @Override
    public String toString(){
        return "DeadlinePost{author=" + getAuthor() + ", responsibleUser="
                + responsibleUser + ", msg=" + getMsg() + ", deadline="
                + deadline + ", currentPrio=" + currentPrio + ", dateCreated="
                + getDateCreated() + ", dateModified=" + getDateModified()
                + '}';
    }
}
