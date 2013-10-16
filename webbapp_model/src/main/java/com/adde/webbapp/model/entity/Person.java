package com.adde.webbapp.model.entity;

import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Simple user.
 * Test
 * 
 * Ideer till framtiden:
 * Fler attribut: kort beskrivning, företag, klass, ålder, username osv...
 * Nickname unique, should not be able to change name.
 * Assuming here that username is unique, checked in GUI and not here.
 * 
 * @author ehannes
 */
@Entity
public class Person extends AbstractEntity implements Serializable {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    @Temporal(TemporalType.DATE)
    private GregorianCalendar calendar;
    private String firstname;
    private String lastname;
    
    public Person() {
    }
    
    public Person(String nickname, String email, String password) {
        this.username = nickname;
        this.password = password;
        this.email = email;
        calendar = new GregorianCalendar();
    }
    
    public String getUserName() {
        return username;
    }
    
    public String getFirstName(){
        return firstname;
    }
    
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastName() {
        return lastname;
    }
    
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getDateCreated() {
        return "User created " + calendar.get(GregorianCalendar.DAY_OF_MONTH)
                + "/" + (calendar.get(GregorianCalendar.MONTH)+1)
                + " " + calendar.get(GregorianCalendar.YEAR);
    }
    
    @Override
    public String toString(){
        return "User{id: " + getId() + ", Nickname: " + username + ", First name: " +
                firstname + ", Last name: " + lastname + ", Email: " + email +
                ", " + getDateCreated() + "}";
    }
}