package com.adde.webbapp_model;

import java.util.Date;
import java.util.Random;

public class Post {
    private User author;
    private Date dateCreated;
    private Date dateModified;
    private String msg;
    private long id;
    
    public Post(User author, String msg){
        this.author = author;
        this.msg = msg;
        dateCreated = new Date(System.currentTimeMillis());
        dateModified = dateCreated;
        id = new Long(new Random().nextInt(777));
    }
    
    public Long getId(){
        System.out.println(this.getClass().getName() + ": Post.getId() returned " + id);
        return id;
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
        dateModified = new Date(System.currentTimeMillis());
    }
    
    @Override
    public String toString(){
        return "Post{author=" + author + ", msg=" + msg + ", dateCreated="
            + dateCreated + ", dateModified=" + dateModified + '}';
    }
    
    @Override
    public boolean equals(Object o){
        boolean result = false;
        if(o instanceof Post){
            Post o2 = (Post) o;
            result = o2.getAuthor() != null && o2.getAuthor().equals(author)
                    && o2.getMsg() != null && o2.getMsg().equals(msg)
                    && o2.getDateCreated() != null
                    && o2.getDateCreated().equals(dateCreated)
                    && o2.getDateModified() != null
                    && o2.getDateModified().equals(dateModified)
                    && o2.getId() == id;
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.author != null ? this.author.hashCode() : 0);
        hash = 29 * hash + (this.dateCreated != null ? this.dateCreated.hashCode() : 0);
        hash = 29 * hash + (this.dateModified != null ? this.dateModified.hashCode() : 0);
        hash = 29 * hash + (this.msg != null ? this.msg.hashCode() : 0);
        return hash;
    }
}