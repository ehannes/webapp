package com.adde.webbapp_model;

import java.util.Date;

public class Post {
    User author;
    Date dateCreated;
    Date dateModified;
    String msg;
    
    /**
     * @param author Author
     * @param msg Message
     */
    public Post(User author, String msg){
        this.author = author;
        this.msg = msg;
        dateCreated = new Date();
        dateModified = dateCreated;
    }
    
    public Date getDateCreated(){
        return dateCreated;
    }
    
    public Date getDateModified(){
        return dateModified;
    }
    
    public User getAuthor(){
        return author;
    }
    
    public String getMsg(){
        return msg;
    }
    
    public void setMsg(String msg){
        this.msg = msg;
        dateModified = new Date();
    }
}