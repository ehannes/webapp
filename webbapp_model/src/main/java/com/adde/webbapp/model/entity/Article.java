package com.adde.webbapp.model.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.CascadeType;
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

    @OneToMany(cascade={CascadeType.ALL})
    private List<SimpleEditorEntry> editorEntries;
    private String title;
    private String content;

    public Article() {}
    
    public Article(String content, String title) {
        this.content = content;
        this.title = title;
    }
    
    public List<SimpleEditorEntry> getEditors() {
        return editorEntries;
    }

    public void setEditors(List<SimpleEditorEntry> editors) {
        this.editorEntries = editors;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SimpleEditorEntry> getEditorEntries() {
        return editorEntries;
    }
    
    public void setEditorEntries(List<SimpleEditorEntry> editorEntries) {
        this.editorEntries = editorEntries;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String allEditors() {
        String result = "";
        for(SimpleEditorEntry see : editorEntries){
            result += see.toString();
        }
        return result;
    }
    
    @Override
    public String toString() {
        return "Article{" + super.toString() + " Title: " + title
                + " Content: " + content + allEditors();
    }
}