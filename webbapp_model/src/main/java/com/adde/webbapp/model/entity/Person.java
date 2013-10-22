package com.adde.webbapp.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author ehannes
 */
@Entity
public class Person extends AbstractEntity implements Serializable {
    
    @Column(nullable=false)
    private String username;
    
    @Column(nullable=false)
    private String password;
    
    @Column(nullable=false)
    private String email;
    
    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    private Calendar dateCreated;
    private String firstname;
    private String lastname;
    
    public Person() {
    }
    
    public Person(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public String getUserName() {
        return username;
    }
    
    public void setUserName(String username) {
        this.username = username;
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
    
    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }
    
    @Override
    public String toString(){
        return super.toString() + ", username: " + username + ", first name: " + 
                firstname + ", last name: " + ", email: " + email + dateToString();
    }
    
    private String dateToString() {
        return ", user created " + dateCreated.get(GregorianCalendar.DAY_OF_MONTH)
                + "/" + (dateCreated.get(GregorianCalendar.MONTH)+1)
                + " " + dateCreated.get(GregorianCalendar.YEAR);
    }
}