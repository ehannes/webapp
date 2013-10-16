package com.adde.webbapp.model.entity;

import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author ehannes
 */
@Entity
public class SimpleEditorEntry extends AbstractEntity implements Serializable {
    
    @ManyToOne(cascade={CascadeType.REFRESH})
    private Person person;
    @Temporal(TemporalType.DATE)
    private GregorianCalendar modificationTime;
    //@ManyToOne(cascade={CascadeType.REFRESH})
    //private Article article;

    public SimpleEditorEntry() {}
    
    public SimpleEditorEntry(Person person) {
        this.person = person;
        this.modificationTime = modificationTime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }   
 
    public GregorianCalendar getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(GregorianCalendar modificationTime) {
        this.modificationTime = modificationTime;
    }

    /*public Article getArticle() {
        return article;
    }*/
    
    /*@Override
    public String toString() {
        return "SimpleEditorEntry{" + super.toString() + ", Person: " + person.toString()
                + ", Edited: " + calendar.get(GregorianCalendar.DAY_OF_MONTH)
                + "/" + (calendar.get(GregorianCalendar.MONTH)+1)
                + " " + calendar.get(GregorianCalendar.YEAR)
                + " at " + calendar.get(GregorianCalendar.HOUR)
                + ":" + calendar.get(GregorianCalendar.MINUTE)
                + ":" + calendar.get(GregorianCalendar.SECOND);
    }*/
}