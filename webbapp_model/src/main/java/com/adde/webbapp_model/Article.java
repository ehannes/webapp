package com.adde.webbapp_model;

import com.adde.webbapp_model_util.AbstractEntity;
import java.io.Serializable;
import java.util.*;
import javax.persistence.Entity;
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

    //@ManyToMany
    //private LinkedList<SimpleEditorEntry> editors;
    private String title;
    private String content;
    @Temporal(TemporalType.DATE)
    private GregorianCalendar dateCreated;
    @Temporal(TemporalType.DATE)
    private GregorianCalendar dateModified;

    public Article() {}
    
    public Article(Person editor, String content, String title) {
        this.content = content;
        dateCreated = new GregorianCalendar();
        dateModified = dateCreated;
        this.title = title;
        EditorDAO.newInstance().add(new SimpleEditorEntry(editor,dateCreated, this));//editors.addFirst(new SimpleEditorEntry(editor, dateModified));
    }
    
    public void update(Person editor, String newContent, String title) {
        content = newContent;
        dateModified = new GregorianCalendar();
        EditorDAO.newInstance().add(new SimpleEditorEntry(editor, dateModified, this));
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public List<SimpleEditorEntry> getEditEntriesByPerson(Person p) {
        List<SimpleEditorEntry> found = new ArrayList<>();
        for(SimpleEditorEntry entry : EditorDAO.newInstance().getAll()){
            if(entry.getKey().equals(p)){
                found.add(entry);
            }
        }
        return found;
    }
    
    /*public LinkedList<SimpleEditorEntry> getEditEntries(int n) {
        LinkedList<SimpleEditorEntry> result = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            result.add(editors.get(i));
        }
        return result;
    }*/
    
    public List<Person> getEditors() {
        List<Person> found = new ArrayList<>();
        for(SimpleEditorEntry entry : EditorDAO.newInstance().getAll()){
            found.add(entry.getKey());
        }
        return found;
    }
    
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
    
    @Override
    public String toString() {
        return "Article{" + super.toString() + " Title: " + title + " Content: " + content
                + " dateCreated: " + calendarToString(dateCreated) + 
                " dateModified: " + calendarToString(dateModified)+ " Editors: "
                + "}"; //editors.toString() + "}";
    }
}