/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Person;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;

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
    
    public List<Person> getByUserName(String username) {
        EntityManager em = getEntityManager();
        List<Person> found = em.createQuery("select p from Person p where"
                + " p.username = :username", Person.class).
                setParameter("username", username).getResultList();
        return found;
    }
    
    public List<Person> getByFirstName(String firstname) {
        EntityManager em = getEntityManager();
        List<Person> found = em.createQuery("select p from Person p where"
                + " p.firstname = :firstname", Person.class).
                setParameter("firstname", firstname).getResultList();
        return found;
    }

    public List<Person> getByLastName(String lastname) {
        EntityManager em = getEntityManager();
        List<Person> found = em.createQuery("select p from Person p where"
                + " p.lastname = :lastname", Person.class).
                setParameter("lastname", lastname).getResultList();
        return found;
    }

    public List<Person> getByEmail(String email) {
        EntityManager em = getEntityManager();
        List<Person> found = em.createQuery("select p from Person p where"
                + " p.email = :email", Person.class).
                setParameter("email", email).getResultList();
        return found;
    }
}
