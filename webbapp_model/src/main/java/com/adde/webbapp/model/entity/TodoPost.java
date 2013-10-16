package com.adde.webbapp.model.entity;

import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TodoPost extends Post {
    @Temporal(TemporalType.DATE)
    private Calendar deadline; //may be null
    public static enum Priority{LOW, MEDIUM, HIGH};
    private Priority currentPrio;
    @ManyToMany(cascade = {CascadeType.REFRESH})
    private List<Person> assignedTo;

    //To satisfy requirements of @Entity annotation. Don't ever use it!
    public TodoPost() {
        this(null,null);
    }

    public TodoPost(Person author, String msg){
        super(author, msg);
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public Priority getCurrentPrio() {
        return currentPrio;
    }

    public void setCurrentPrio(Priority currentPrio) {
        this.currentPrio = currentPrio;
    }

    public List<Person> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(List<Person> assignedTo) {
        this.assignedTo = assignedTo;
    }
}