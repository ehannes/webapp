package com.adde.webbapp_model;

import java.util.GregorianCalendar;
import java.util.Random;

public class Post {
    private User author;
    private GregorianCalendar dateCreated;
    private GregorianCalendar dateModified;
    private String msg;
    private final long id;
    
    public Post(User author, String msg){
        this.author = author;
        this.msg = msg;
        dateCreated = new GregorianCalendar();
        dateModified = dateCreated;
        id = new Long(new Random().nextInt(777));
    }
    
    public Long getId(){
        System.out.println(this.getClass().getName() + ": Post.getId() returned " + id);
        return id;
    }
    
    public GregorianCalendar getDateCreated(){
        return dateCreated;
    }
    
    public GregorianCalendar getDateModified(){
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
        dateModified = new GregorianCalendar();
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
            result = o2.getId() == id;
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
}