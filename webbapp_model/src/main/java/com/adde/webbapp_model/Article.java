package com.adde.webbapp_model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Article {

    private User author;
    // Possibly use another data structure or a new class for this.
    private HashMap<Date, User> editors;
    private String content;
    private Date dateCreated;
    private Date dateModified;
    private Long id;

    public Article(User author, String content) {
        this.author = author;
        this.content = content;
        //editors = new LinkedList<User>();
        editors = new HashMap<Date, User>();
        dateCreated = new Date();
        dateModified = dateCreated;
        id = new Long(new Random().nextInt(1000));
    }

    public void update(User editor, String newContent) {
        editors.put(new Date(), editor);
        content = newContent;
        dateModified = new Date();
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
    
    public Long getId() {
        return id;
    }
    
    // Equals means same id. Sufficient?
    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        if(other instanceof Article) {
            Article o = (Article) other;
            // Extract the primitive type for comparison
            if(o.getId().longValue() == this.id.longValue()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        return "Article{Id: " + id + " Author: " + author + " Content: " + content
                + " dateCreated: " + dateCreated + " dateModified: " + dateModified;
    }
}