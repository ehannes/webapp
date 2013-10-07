package com.adde.webbapp_model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * Editors?
 * Hur göra med Title? Ska allt uppdateras i update() eller två separata metoder
 * för title och content?
 */
public class Article {

    private User author;
    // Possibly use another data structure or a new class for this.
    private HashMap<Date, User> editors;
    private String title;
    private String content;
    private Date dateCreated;
    private Date dateModified;
    private final long id;

    public Article(User author, String content, String title) {
        this.author = author;
        this.content = content;
        //editors = new LinkedList<User>();
        editors = new HashMap<Date, User>();
        dateCreated = new Date();
        dateModified = dateCreated;
        this.title = title;
        id = new Long(new Random().nextInt(1000));
    }
    
    public Article(User author, String content, String title, long id) {
        this.author = author;
        this.content = content;
        //editors = new LinkedList<User>();
        editors = new HashMap<Date, User>();
        dateCreated = new Date();
        dateModified = dateCreated;
        this.title = title;
        this.id = id;
    }

    public void update(User editor, String newContent, String title) {
        editors.put(new Date(), editor);
        content = newContent;
        dateModified = new Date();
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public Map<Date, User> getEditEntries() {
        return editors;
    }

    public String getContent() {
        return content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }
    
    public long getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        if(other instanceof Article) {
            Article o = (Article) other;
            if(o.getId() == this.id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }


    
    @Override
    public String toString() {
        return "Article{Id: " + id + " Author: " + author + " Content: " + content
                + " dateCreated: " + dateCreated + " dateModified: " + dateModified;
    }
}