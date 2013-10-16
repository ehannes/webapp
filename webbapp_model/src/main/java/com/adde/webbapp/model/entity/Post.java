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
    @Temporal(TemporalType.DATE)
    private Calendar dateCreated;
    @Temporal(TemporalType.DATE)
    private Calendar dateModified;
    private String msg;

    //necessary to be an entity, NEVER USE!!!
    public Post() {
        this(null,null);
    }
    
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

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Calendar getDateModified() {
        return dateModified;
    }

    public void setDateModified(Calendar dateModified) {
        this.dateModified = dateModified;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}