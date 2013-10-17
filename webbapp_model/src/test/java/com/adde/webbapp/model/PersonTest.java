package com.adde.webbapp.model;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PersonDAO;
import com.adde.webbapp.model.entity.Person;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Person.
 *
 * @author hannes
 */
public class PersonTest {
    Person person1, person2;
    String person1_pw, person2_pw;

    PersonDAO personDAO;

    @Before
    public void before() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        personDAO = daoFactory.getPersonDAO();
        
        person1_pw = "HsdE3324!gh";
        person1 = new Person("testperson_username", "testperson@testpersons.com", person1_pw);
        person1.setFirstName("person1_firstname");
        person1.setLastName("person1_lastname");
        
        person2_pw = "YhIJKd!ad";
        person2 = new Person("testperson 2", "testperson2@testpersons.com", person2_pw);
        
        personDAO.add(person1);
        personDAO.add(person2);
        
        List<Person> allPersons = personDAO.getAll();
        
        assertTrue(allPersons.contains(person1) && allPersons.contains(person2) && allPersons.size() == 2);
    }
    
    @After
    public void after() {
        List<Person> allPersons = personDAO.getAll();
        for(Person p : allPersons) {
            personDAO.remove(p.getId());
        }
        
        assertTrue(personDAO.getCount() == 0);
    }

    @Test
    public void update() {
        String person2_updated_pw = "nEw!paSSwoRD";
        person2.setPassword(person2_updated_pw);
        personDAO.update(person2);
        assertTrue(personDAO.find(person2.getId()).getPassword().equals(person2_updated_pw));
    }
    
    @Test
    public void getBy() {
        assertTrue(personDAO.getByUserName(person1.getUserName()).equals(person1));
        assertTrue(personDAO.getByFirstName(person1.getFirstName()).contains(person1));
        assertTrue(personDAO.getByLastName(person1.getLastName()).contains(person1));
        assertTrue(personDAO.getByEmail(person1.getEmail()).equals(person1));
    }
    
    @Test
    public void equalityTest() {
        assertFalse(person1.equals(person2));
        assertFalse(person2.equals(person1));

        assertTrue(person1.equals(person1));
    }
    
    @Test
    public void printUser() {
        Logger.getAnonymousLogger().log(Level.INFO, "Two users created with "
                + "attributes: {0} and {1}", new Object[]{person1, person2});
    }
}