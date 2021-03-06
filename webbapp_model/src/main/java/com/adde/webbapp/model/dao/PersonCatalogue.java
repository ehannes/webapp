package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Person;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
public class PersonCatalogue extends AbstractDAO<Person, Long> {

    public static PersonCatalogue newInstance() {
        return new PersonCatalogue();
    }

    private PersonCatalogue() {
        super(Person.class);
    }
    
    @Override
    public void add(Person person) {
        final Calendar dateCreated = new GregorianCalendar();
        person.setDateCreated(dateCreated);
        super.add(person);
    }
    
    public Person getByUserName(String username) {
        EntityManager em = getEntityManager();
        try{
            Query q = em.createQuery("select p from Person p where"
                + " p.username = :username", Person.class).
                setParameter("username", username);
            Object object = q.getSingleResult();
            if(object instanceof Person) {
                return (Person) object;
            }
            return null;
        } catch(NonUniqueResultException e) {
            Logger.getAnonymousLogger().log(Level.INFO, 
                    "Multiple persons with the same username in database.");
            e.printStackTrace();
            return null;
        } catch(NoResultException e){
            return null;
        } finally{
            if(em != null){
                em.close();
            }
        }
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

    public Person getByEmail(String email) {
        EntityManager em = getEntityManager();
        try{
            Query q = em.createQuery("select p from Person p where"
                + " p.email = :email", Person.class).
                setParameter("email", email);
            return (Person) q.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }
}
