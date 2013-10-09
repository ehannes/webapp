package com.adde.webapp_model;

import java.util.*;

/*
 * Editors?
 * Hur göra med Title? Ska allt uppdateras i update() eller två separata metoder
 * för title och content?
 * 
 * Features (ev.):
 * - taggar
 * - söka igenom olika artiklar
 */
public class Article {

    private User author;
    private LinkedList<AbstractMap.SimpleEntry<User, Date>> editors;
    private String title;
    private String content;
    private Date dateCreated;
    private Date dateModified;
    private final long id;

    public Article(long id, User author, String content, String title) {
        this.author = author;
        this.content = content;
        editors = new LinkedList<AbstractMap.SimpleEntry<User, Date>>();
        dateCreated = new Date();
        dateModified = dateCreated;
        this.title = title;
        this.id = id;
    }
    
    public Article(User author, String content, String title) {
        this.author = author;
        this.content = content;
        editors = new LinkedList<AbstractMap.SimpleEntry<User, Date>>();
        dateCreated = new Date();
        dateModified = dateCreated;
        this.title = title;
        id = new Long(new Random().nextInt(1000));
    }
    
    public void update(User editor, String newContent, String title) {
        content = newContent;
        dateModified = new Date();
        editors.addFirst(new AbstractMap.SimpleEntry(editor, dateModified));
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }
    
    public LinkedList<AbstractMap.SimpleEntry<User, Date>> getEditEntries() {
        return getEditEntries(editors.size());
    }

    public LinkedList<AbstractMap.SimpleEntry<User, Date>> getEditEntries(int n) {
        LinkedList<AbstractMap.SimpleEntry<User, Date>> result = new LinkedList<AbstractMap.SimpleEntry<User, Date>>();
        for(int i = 0; i < n; i++) {
            result.add(editors.get(i));
        }
        return result;
    }

    public List<User> getEditors() {
        return getEditors(editors.size());
    }
    
    public List<User> getEditors(int n) {
        List<User> result = new ArrayList<User>();
        for(int i = 0; i < n; i++) {
            result.add(editors.get(i).getKey());
        }
        return result;
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