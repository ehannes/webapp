package com.adde.webbapp_model;

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

    private LinkedList<AbstractMap.SimpleEntry<User, GregorianCalendar>> editors;
    private String title;
    private String content;
    private GregorianCalendar dateCreated;
    private GregorianCalendar dateModified;
    private final long id;

    public Article(long id, User editor, String content, String title) {
        this.content = content;
        editors = new LinkedList<AbstractMap.SimpleEntry<User, GregorianCalendar>>();
        dateCreated = new GregorianCalendar();
        dateModified = dateCreated;
        this.title = title;
        this.id = id;
        editors.addFirst(new AbstractMap.SimpleEntry(editor, dateModified));
    }
    
    public Article(User editor, String content, String title) {
        this(new Long(new Random().nextInt(1000)), editor, content, title);
    }
    
    public void update(User editor, String newContent, String title) {
        content = newContent;
        dateModified = new GregorianCalendar();
        editors.addFirst(new AbstractMap.SimpleEntry(editor, dateModified));
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public LinkedList<AbstractMap.SimpleEntry<User, GregorianCalendar>> getEditEntries() {
        return getEditEntries(editors.size());
    }

    public LinkedList<AbstractMap.SimpleEntry<User, GregorianCalendar>> getEditEntries(int n) {
        LinkedList<AbstractMap.SimpleEntry<User, GregorianCalendar>> result = new LinkedList<AbstractMap.SimpleEntry<User,GregorianCalendar>>();
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

    public GregorianCalendar getDateCreated() {
        return dateCreated;
    }

    public GregorianCalendar getDateModified() {
        return dateModified;
    }
    public String calendarToString(GregorianCalendar c) {
        return  c.get(GregorianCalendar.DAY_OF_MONTH)
                + "/" + (c.get(GregorianCalendar.MONTH) + 1)
                + " " + c.get(GregorianCalendar.YEAR);
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
        List<User> editorList = new ArrayList<User>();
        for(AbstractMap.SimpleEntry se : editors) {
            
            // Necessary to type cast here?
            editorList.add((User) se.getKey());
        }
        return "Article{Id: " + id + " Title: " + title + " Content: " + content
                + " dateCreated: " + calendarToString(dateCreated) + 
                " dateModified: " + calendarToString(dateModified)+ " Editors: "
                + editorList + "}";
    }
}