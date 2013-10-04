package com.adde.webbapp_model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Article {

    User author;
    //List<User> editors;
    HashMap<Date, User> editors;
    String content;
    Date dateCreated;
    Date dateModified;

    public Article(User author, String content) {
        this.author = author;
        this.content = content;
        //editors = new LinkedList<User>();
        editors = new HashMap<Date, User>();
        dateCreated = new Date();
        dateModified = dateCreated;
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
}