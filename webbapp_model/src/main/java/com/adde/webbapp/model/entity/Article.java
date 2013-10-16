package com.adde.webbapp.model.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author ehannes
 * 
 * Title? Which arguments to update()? Depends on front end... One update()
 * for title, one for content and one for both?
 * 
 * Features:
 * - tags
 * - search articles
 **/
@Entity
public class Article extends AbstractEntity implements Serializable {

    @OneToMany(cascade={CascadeType.ALL})
    private List<SimpleEditorEntry> editors;
    private String title;
    private String content;
    @Temporal(TemporalType.DATE)
    private Calendar dateCreated;
    @Temporal(TemporalType.DATE)
    private Calendar dateModified;

    public Article() {}
    
    public Article(String content, String title) {
        this.content = content;
        this.title = title;
    }
    
    /*public void update(Person editor, String newContent, String title) {
    content = newContent;
    dateModified = new GregorianCalendar();
    editors.addFirst(new SimpleEditorEntry(editor, dateModified));
    this.title = title;
    }*/
    public List<SimpleEditorEntry> getEditors() {
        return editors;
    }

    public void setEditors(List<SimpleEditorEntry> editors) {
        this.editors = editors;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    /*public List<SimpleEditorEntry> getEditEntriesByPerson(Person p) {
        List<SimpleEditorEntry> found = new ArrayList<>();
        for(SimpleEditorEntry entry : EditorDAO.newInstance().getAll()){
            if(entry.getKey().equals(p)){
                found.add(entry);
            }
        }
        return found;
    }*/
    
    /*public LinkedList<SimpleEditorEntry> getEditEntries(int n) {
        LinkedList<SimpleEditorEntry> result = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            result.add(editors.get(i));
        }
        return result;
    }*/
    
    /*public List<Person> getEditors() {
        List<Person> found = new ArrayList<>();
        for(SimpleEditorEntry entry : EditorDAO.newInstance().getAll()){
            found.add(entry.getKey());
        }
        return found;
    }*/
    
    /*
    public List<Person> getEditors(int n) {
        List<Person> result = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            result.add(editors.get(i).getKey());
        }
        return result;
    }*/
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Calendar getDateModified() {
        return dateModified;
    }

    public void setDateModified(Calendar dateModified) {
        this.dateModified = dateModified;
    }

    private String calendarToString(GregorianCalendar c) {
        return  c.get(GregorianCalendar.DAY_OF_MONTH)
                + "/" + (c.get(GregorianCalendar.MONTH) + 1)
                + " " + c.get(GregorianCalendar.YEAR);
    }
    
    /*@Override
    public String toString() {
        return "Article{" + super.toString() + " Title: " + title + " Content: " + content
                + " dateCreated: " + calendarToString(dateCreated) + 
                " dateModified: " + calendarToString(dateModified)+ " Editors: "
                + "}"; //editors.toString() + "}";
    }*/
}