package com.adde.webbapp.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Post extends AbstractEntity implements Serializable {
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Person author;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar timeCreated;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar timeModified;
    private String msg;

    public Post() {}
    
    public Post(Person author, String msg){
        this.author = author;
        this.msg = msg;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Calendar getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Calendar timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Calendar getTimeModified() {
        return timeModified;
    }

    public void setTimeModified(Calendar timeModified) {
        this.timeModified = timeModified;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}