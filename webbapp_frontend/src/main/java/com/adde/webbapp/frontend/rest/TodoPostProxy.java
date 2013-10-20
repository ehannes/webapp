/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.frontend.rest;

//For translation from XML to JSON

import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.TodoPost;
import java.util.Calendar;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="TodoPost")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "PostType")
public class TodoPostProxy {
    
    // The wrapped product
    private TodoPost todoPost;

    protected TodoPostProxy() { }
   
    public TodoPostProxy(TodoPost todoPost) {
        this.todoPost = todoPost;
    }
    
    @XmlElement(required = true)
    public Long getId() {
        return todoPost.getId();
    }
    
    @XmlElement (required = true)
    public String getMsg() {
        return todoPost.getMsg();
    }

    @XmlElement (required=true)
    public Calendar getTimeCreated(){
        return todoPost.getTimeCreated();
    }
    
    @XmlElement (required=true)
    public Calendar getTimeModified(){
        return todoPost.getTimeCreated();
    }
    
    @XmlElement (required = true)
    public Calendar getDeadline() {
        return todoPost.getDeadline();
    }
    
    @XmlElement (required = true)
    public TodoPost.Priority getPriority() {
        return todoPost.getPriority();
    }
    
    @XmlElement (required = true)
    public GenericEntity<List<Person>> getAssignedTo() {
        return new GenericEntity<List<Person>>(todoPost.getAssignedTo()) {};
    }
}