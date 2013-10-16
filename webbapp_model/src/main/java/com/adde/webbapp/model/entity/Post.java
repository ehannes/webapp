package com.adde.webbapp.model.entity;

import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

public abstract class Post<T> extends AbstractEntity implements Serializable {
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Person author;
    @NotNull
    @Temporal(TemporalType.DATE)
    private GregorianCalendar dateCreated;
    @NotNull
    @Temporal(TemporalType.DATE)
    private GregorianCalendar dateModified;
    private String msg;
    private T context;
    
    public Post(T context, Person author, String msg){
        this.author = author;
        this.msg = msg;
        dateCreated = new GregorianCalendar();
        dateModified = dateCreated;
    }
    
    public GregorianCalendar getDateCreated(){
        return dateCreated;
    }
    
    public T getContext(){
        return context;
    }

    public String getStringDateCreated() {
        return "Post created " + dateCreated.get(GregorianCalendar.DAY_OF_MONTH)
                + "/" + (dateCreated.get(GregorianCalendar.MONTH)+1)
                + " " + dateCreated.get(GregorianCalendar.YEAR);
    }
    
    public GregorianCalendar getDateModified(){
        return dateModified;
    }
    
    public String getStringDateModified() {
        return "Post modified " + dateModified.get(GregorianCalendar.DAY_OF_MONTH)
                + "/" + (dateModified.get(GregorianCalendar.MONTH)+1)
                + " " + dateModified.get(GregorianCalendar.YEAR);
    }
    
    public Person getAuthor(){
        return author;
    }
    
    public String getMsg(){
        return msg;
    }
    
    public void setMsg(String msg){
        this.msg = msg;
        dateModified = new GregorianCalendar();
    }
    
    @Override
    public abstract String toString();
}