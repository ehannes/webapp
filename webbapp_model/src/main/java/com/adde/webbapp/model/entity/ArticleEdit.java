package com.adde.webbapp.model.entity;

import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Belongs to Article. Adds feature to track editors
 * to an Article (when and who).
 * 
 * @author ehannes
 */
@Entity
public class ArticleEdit extends AbstractEntity implements Serializable {
    
    @ManyToOne(cascade={CascadeType.REFRESH})
    @JoinColumn(nullable=false)
    private Person editor;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private GregorianCalendar editTime;

    public ArticleEdit() {}
    
    public ArticleEdit(Person person) {
        this.editor = person;
    }

    public Person getEditor() {
        return editor;
    }

    public void setEditor(Person editor) {
        this.editor = editor;
    }   
 
    public GregorianCalendar getEditTime() {
        return editTime;
    }

    public void setEditTime(GregorianCalendar editTime) {
        this.editTime = editTime;
    }
    
    @Override
    public String toString() {
        return "SimpleEditorEntry{" + super.toString() + ", Person: " + editor.toString()
                + ", Edited: " + editTime.get(GregorianCalendar.DAY_OF_MONTH)
                + "/" + (editTime.get(GregorianCalendar.MONTH)+1)
                + " " + editTime.get(GregorianCalendar.YEAR)
                + " at " + editTime.get(GregorianCalendar.HOUR)
                + ":" + editTime.get(GregorianCalendar.MINUTE)
                + ":" + editTime.get(GregorianCalendar.SECOND);
    }
}