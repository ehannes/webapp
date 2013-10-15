package com.adde.webbapp_model;

import com.adde.webbapp_model_util.AbstractEntity;
import java.util.GregorianCalendar;
import javax.persistence.ManyToOne;

public class Post extends AbstractEntity {
    @ManyToOne
    private Person author;
    private GregorianCalendar dateCreated;
    private GregorianCalendar dateModified;
    private String msg;
    
    public Post(Person author, String msg){
        this.author = author;
        this.msg = msg;
        dateCreated = new GregorianCalendar();
        dateModified = dateCreated;
    }
    
    public GregorianCalendar getDateCreated(){
        return dateCreated;
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
    public String toString(){
        return "Post{id=" + getId() + ", author=" + author + ", msg=" + msg + ", dateCreated="
            + getStringDateCreated() + ", dateModified=" + getStringDateModified() + '}';
    }
    
    @Override
    public boolean equals(Object o){
        boolean result = false;
        if(o instanceof Post){
            result = super.equals(o);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (getId() ^ (getId() >>> 32));
        return hash;
    }
}