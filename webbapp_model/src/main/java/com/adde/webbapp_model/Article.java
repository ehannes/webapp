package com.adde.webbapp_model;

import com.adde.webbapp_model_util.AbstractEntity;
import java.io.Serializable;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

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

    @OneToMany
    private LinkedList<SimpleEditorEntry> editors;
    private String title;
    private String content;
    private GregorianCalendar dateCreated;
    private GregorianCalendar dateModified;

    public Article() {}
    
    public Article(Person editor, String content, String title) {
        this.content = content;
        dateCreated = new GregorianCalendar();
        dateModified = dateCreated;
        this.title = title;
        editors.addFirst(new SimpleEditorEntry(editor, dateModified));
    }
    
    public void update(Person editor, String newContent, String title) {
        content = newContent;
        dateModified = new GregorianCalendar();
        editors.addFirst(new SimpleEditorEntry(editor, dateModified));
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public LinkedList<SimpleEditorEntry> getEditEntries() {
        return getEditEntries(editors.size());
    }

    public LinkedList<SimpleEditorEntry> getEditEntries(int n) {
        LinkedList<SimpleEditorEntry> result = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            result.add(editors.get(i));
        }
        return result;
    }

    public List<Person> getEditors() {
        return getEditors(editors.size());
    }
    
    public List<Person> getEditors(int n) {
        List<Person> result = new ArrayList<>();
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
    
    @Override
    public String toString() {
        return "Article{" + super.toString() + " Title: " + title + " Content: " + content
                + " dateCreated: " + calendarToString(dateCreated) + 
                " dateModified: " + calendarToString(dateModified)+ " Editors: "
                + editors.toString() + "}";
    }
}