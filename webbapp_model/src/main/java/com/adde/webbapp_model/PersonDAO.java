/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import com.adde.webbapp_model_util.AbstractDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eric
 */
public class PersonDAO extends AbstractDAO<Person, Long> {

    public static PersonDAO newInstance(String puName) {
        return new PersonDAO(puName);
    }

    private PersonDAO(String puName) {
        super(Person.class, puName);
    }

    public List<Person> getByUserName(String name) {
        List<Person> found = new ArrayList<>();
        for (Person p : getRange(0, getCount())) {
            if (p.getUserName().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }

    public List<Person> getUsers() {
        List<Person> found = new ArrayList<>();
        for (Person p : getRange(0, getCount())) {
            found.add(p);
        }
        return found;
    }

    public List<Person> getByFirstName(String name) {
        List<Person> found = new ArrayList<>();
        for (Person p : getRange(0, getCount())) {
            if (p.getFirstName().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }

    public List<Person> getByLastName(String name) {
        List<Person> found = new ArrayList<>();
        for (Person p : getRange(0, getCount())) {
            if (p.getLastName().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }

    public List<Person> getByEmail(String name) {
        List<Person> found = new ArrayList<>();
        for (Person p : getRange(0, getCount())) {
            if (p.getEmail().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }
}
