package com.adde.webbapp.model;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PersonDAO;
import com.adde.webbapp.model.entity.Person;
import java.util.ArrayList;
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
        assertTrue(personDAO.getByUserName(person1.getUserName()).contains(person1));
        assertTrue(personDAO.getByFirstName(person1.getFirstName()).contains(person1));
        assertTrue(personDAO.getByLastName(person1.getLastName()).contains(person1));
        assertTrue(personDAO.getByEmail(person1.getEmail()).contains(person1));
    }
    
    @Test
    public void notNull() {
        // Username, email and password shouldn't have any values set to null
        //Person person = new Person()
    }
}
//    @Test
//    public void nullTest() {
//        //New user should not have any values set to null
//        
//        assertTrue(nullTest.getDateCreated() != null);
//        assertTrue(nullTest.getEmail() != null);
//        assertTrue(nullTest.getFirstName() != null);
//        assertTrue(nullTest.getLastName() != null);
//        assertTrue(nullTest.getUserName() != null);
//    }
//    
//    @Test
//    public void getSetTest() {
//        assertTrue(user1.getUserName().equals("User1"));
//        
//        user1.setFirstName("newName");
//        assertTrue(user1.getFirstName().equals("newName"));
//        
//        user1.setLastName("newLastName");
//        assertTrue(user1.getLastName().equals("newLastName"));
//        
//        user1.setEmail("newEmail");
//        assertTrue(user1.getEmail().equals("newEmail"));
//    }
//    
//    @Ignore
//    @Test
//    public void equalityTest() {
//        //Two users with different Id's should not be equal
//        /*
//        assertFalse(user1.equals(user2));
//        assertFalse(user2.equals(user1));
//
//        assertTrue(user1.equals(user1));
//        
//        //New user with same Id as user1. Though different nicknames, users should now be equal
//        Person user3 = new Person(user1.getId(), "User3", "email3");
//        assertTrue(user3.equals(user1));
//        assertTrue(user1.equals(user3));
//        
//        //Same nickname doesn't mean equal
//        Person user4 = new Person("User1", "email1");
//        assertTrue(user4.getUserName().equals(user1.getUserName()));
//        assertFalse(user4.equals(user1));
//        */
//    }
//    
//    @Test
//    public void printUser() {
//        Logger.getAnonymousLogger().log(Level.INFO, "Two users created with attributes: {0} and {1}", new Object[]{user1, user2});
//    }
//}