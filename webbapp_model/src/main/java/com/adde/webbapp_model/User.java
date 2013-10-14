package com.adde.webbapp_model;

import java.util.GregorianCalendar;
import javax.persistence.Entity;

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
public class User extends AbstractEntity {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private GregorianCalendar calendar;
    
    public User() {
    }
    
    public User(String nickname, String email) {
        this.username = nickname;
        initUser(email);
    }
    
    private void initUser(String email) {
        this.email = email;
        this.firstname = "";
        this.lastname = "";
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
    
    public String getDateCreated() {
        return "User created " + calendar.get(GregorianCalendar.DAY_OF_MONTH)
                + "/" + (calendar.get(GregorianCalendar.MONTH)+1)
                + " " + calendar.get(GregorianCalendar.YEAR);
    }
    
    @Override
    public String toString(){
        return super.toString() + ", Nickname: " + username + ", First name: " +
                firstname + ", Last name: " + lastname + ", Email: " + email +
                ", " + getDateCreated() + "}";
    }
}