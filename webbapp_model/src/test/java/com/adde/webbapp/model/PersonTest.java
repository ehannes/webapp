package com.adde.webbapp.model;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PersonCatalogue;
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

    PersonCatalogue personCatalogue;

    @Before
    public void before() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        personCatalogue = daoFactory.getPersonCatalogue();
        
        person1_pw = "HsdE3324!gh";
        person1 = new Person("testperson_username", "testperson@testpersons.com", person1_pw);
        person1.setFirstName("person1_firstname");
        person1.setLastName("person1_lastname");
        
        person2_pw = "YhIJKd!ad";
        person2 = new Person("testperson 2", "testperson2@testpersons.com", person2_pw);
        
        personCatalogue.add(person1);
        personCatalogue.add(person2);
        
        List<Person> allPersons = personCatalogue.getAll();
        
        assertTrue(allPersons.contains(person1) && allPersons.contains(person2) && allPersons.size() == 2);
    }
    
    @After
    public void after() {
        List<Person> allPersons = personCatalogue.getAll();
        for(Person p : allPersons) {
            personCatalogue.remove(p.getId());
        }
        
        assertTrue(personCatalogue.getCount() == 0);
    }

    @Test
    public void update() {
        String person2_updated_pw = "nEw!paSSwoRD";
        person2.setPassword(person2_updated_pw);
        personCatalogue.update(person2);
        assertTrue(personCatalogue.find(person2.getId()).getPassword().equals(person2_updated_pw));
    }
    
    @Test
    public void getBy() {
        assertTrue(personCatalogue.getByUserName(person1.getUserName()).equals(person1));
        assertTrue(personCatalogue.getByFirstName(person1.getFirstName()).contains(person1));
        assertTrue(personCatalogue.getByLastName(person1.getLastName()).contains(person1));
        assertTrue(personCatalogue.getByEmail(person1.getEmail()).equals(person1));
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
    
    @Test
    public void getCount() {
        Logger.getAnonymousLogger().log(Level.INFO, "getCount: {0}", personCatalogue.getCount());
        assertTrue(personCatalogue.getCount() == 2);
        List<Person> personList = personCatalogue.getAll();
        for(Person p : personList){
            personCatalogue.remove(p.getId());
        }
        Logger.getAnonymousLogger().log(Level.INFO, "getCount: {0}", personCatalogue.getCount());
        assertTrue(personCatalogue.getCount() == 0);
    }
}