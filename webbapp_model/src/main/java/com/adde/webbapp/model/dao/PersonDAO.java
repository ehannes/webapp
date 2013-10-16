/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Person;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author eric
 */
public class PersonDAO extends AbstractDAO<Person, Long> {

    public static PersonDAO newInstance() {
        return new PersonDAO();
    }

    private PersonDAO() {
        super(Person.class);
    }
    
    @Override
    public void add(Person person) {
        final Calendar dateCreated = new GregorianCalendar();
        person.setDateCreated(dateCreated);
        super.add(person);
    }
    

    public List<Person> getByUserName(String name) {
        List<Person> found = new ArrayList<>();
        for (Person p : getAll()) {
            if (p.getUserName().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }

    public List<Person> getUsers() {
        List<Person> found = new ArrayList<>();
        for (Person p : getAll()) {
            found.add(p);
        }
        return found;
    }

    public List<Person> getByFirstName(String name) {
        List<Person> found = new ArrayList<>();
        for (Person p : getAll()) {
            if (p.getFirstName().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }

    public List<Person> getByLastName(String name) {
        List<Person> found = new ArrayList<>();
        for (Person p : getAll()) {
            if (p.getLastName().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }

    public List<Person> getByEmail(String name) {
        List<Person> found = new ArrayList<>();
        for (Person p : getAll()) {
            if (p.getEmail().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }
}
