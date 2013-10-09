package com.adde.webapp_model;

import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Random;

/**
 * Simple user.
 * 
 * Ideer till framtiden:
 * Fler attribut: kort beskrivning, företag, klass, ålder, username osv...
 * Nickname unique, should not be able to change name.
 * Assuming here that username is unique, checked in GUI and not here.
 * 
 * @author ehannes
 */
public class User {
    private final long id;
    private final String username;
    private String firstname;
    private String lastname;
    private String email;
    private GregorianCalendar calendar;
    
    public User(String nickname, String email) {
        id = new Long(new Random().nextInt(1000000));
        this.username = nickname;
        initUser(email);
    }
    
    public User(long id, String nickname, String email) {
        this.id = id;
        this.username = nickname;
        initUser(email);
    }
    
    private void initUser(String email) {
        this.email = email;
        this.firstname = "";
        this.lastname = "";
        calendar = new GregorianCalendar();
    }
    
    public long getId() {
        return id;
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
        return "User{Id: " + id + ", Nickname: " + username + ", First name: " +
                firstname + ", Last name: " + lastname + ", Email: " + email +
                ", " + getDateCreated() + "}";
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null) {
            return false;
        }
        if(o instanceof User) {
            User u = (User) o;
            if(u.getId() == this.id) { //equal if same id
                return true;
            }
        }
        return false;
    }
}