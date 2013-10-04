package com.adde.webbapp_model;

import java.util.Date;

public class Post {
    long authorId;
    Date dateCreated;
    Date dateModified;
    String msg;
    
    public Post(long authorId, String msg){
        this.authorId = authorId;
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
    
    public long getAuthor(){
        return authorId;
    }
    
    public String getMsg(){
        return msg;
    }
    
    public void setMsg(String msg){
        this.msg = msg;
        dateModified = new Date();
    }
}