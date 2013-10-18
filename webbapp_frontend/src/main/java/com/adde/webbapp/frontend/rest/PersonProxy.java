package com.adde.webbapp.frontend.rest;


import com.adde.webbapp.model.entity.Person;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="Person")
@XmlType(name = "PersonType")
public class PersonProxy {
    
    private Person person;
    
    protected PersonProxy() {
    }
    
    public PersonProxy(Person person) {
        this.person = person;
    }
    
    @XmlElement(required = true)
    public Long getId() {
        return person.getId();
    }
    
    @XmlElement(required = true)
    public String getUserName() {
        return person.getUserName();
    }
    
    @XmlElement(required = true)
    public String getPassword() {
        return person.getPassword();
    }
    
    @XmlElement(required = true)
    public String getEmail() {
        return person.getEmail();
    }
    
    @XmlElement(required = true)
    public Calendar getDateCreated() {
        return person.getDateCreated();
    }    
    
    @XmlElement(required = true)
    public String getFirstName(){
        return person.getFirstName();
    }
    
    @XmlElement(required = true)
    public String getLastName() {
        return person.getLastName();
    }
}