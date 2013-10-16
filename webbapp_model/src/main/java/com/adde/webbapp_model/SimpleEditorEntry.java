package com.adde.webbapp_model;

import com.adde.webbapp_model_util.AbstractEntity;
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
    private GregorianCalendar calendar;
    //@ManyToOne(cascade={CascadeType.REFRESH})
    //private Article article;

    public SimpleEditorEntry() {}
    
    public SimpleEditorEntry(Person person, GregorianCalendar calendar) {
        this.person = person;
        this.calendar = calendar;
        //this.article = article;
    }
    
    public Person getKey() {
        return person;
    }
    
    public void setKey(Person person){
        this.person = person;
    }

    public GregorianCalendar getValue() {
        return calendar;
    }

    public void setValue(GregorianCalendar calendar) {
        this.calendar = calendar;
    }
    
    /*public Article getArticle() {
        return article;
    }*/
    
    @Override
    public String toString() {
        return "SimpleEditorEntry{" + super.toString() + ", Person: " + person.toString()
                + ", Edited: " + calendar.get(GregorianCalendar.DAY_OF_MONTH)
                + "/" + (calendar.get(GregorianCalendar.MONTH)+1)
                + " " + calendar.get(GregorianCalendar.YEAR)
                + " at " + calendar.get(GregorianCalendar.HOUR)
                + ":" + calendar.get(GregorianCalendar.MINUTE)
                + ":" + calendar.get(GregorianCalendar.SECOND);
    }
   
}