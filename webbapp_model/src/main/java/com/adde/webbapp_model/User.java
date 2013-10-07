package com.adde.webbapp_model;

import java.util.Objects;
import java.util.Random;

/**
 * Simple user.
 * 
 * Ideer till framtiden:
 * Fler attribut: förnamn, efternamn emailadress, kost beskrivning,
 * företag, klass, ålder, nickname osv...
 * 
 * @author ehannes
 */
public class User {
    private final long id;
    private String name;
    
    public User(String name) {
        id = new Long(new Random().nextInt(1000));
        this.name = name;
    }
    
    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String newName) {
        this.name = newName;
    }
    
    @Override
    public String toString(){
        return "User{Name: " + name + " Id: " + id + "}";
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