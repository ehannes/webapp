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

    public static PersonDAO newInstance() {
        return new PersonDAO();
    }

    private PersonDAO() {
        super(Person.class);
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
