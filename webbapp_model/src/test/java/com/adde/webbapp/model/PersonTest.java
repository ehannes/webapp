package com.adde.webbapp.model;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PersonDAO;
import com.adde.webbapp.model.entity.Person;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Person.
 * @author hannes
 */
public class PersonTest {
    PersonDAO personDAO;
    
    @Before
    public void before() {
        DAOFactory daoFactory = new DAOFactory();
        personDAO = daoFactory.getPersonDAO();
    }
    
    @Test
    public void addPersonRemovePerson() {
        //Logger.getAnonymousLogger().log(Level.INFO, "get users: " + personDAO.getUsers());
        String person1_pw = "HsdE3324!gh";
        Person person1 = new Person("testperson", "testperson@testpersons.com", person1_pw);
        
        // Check that the user is not already in the database
        assertFalse(personDAO.getAll().contains(person1));
        
        personDAO.add(person1);
        assertTrue(personDAO.getAll().contains(person1));
        
        personDAO.remove(person1.getId());
        assertFalse(personDAO.getAll().contains(person1));
    }
    
    @Test
    public void updatePerson() {
        String person2_pw = "YhIJKd!ad";
        Person person2 = new Person("testperson 2", "testperson2@testpersons.com", person2_pw);
        personDAO.add(person2);
        assertTrue(personDAO.find(person2.getId()).getPassword().equals(person2_pw));
        
        String person2_updated_pw = "nEw!paSSwoRD";
        person2.setPassword(person2_updated_pw);
        personDAO.update(person2);
        assertTrue(personDAO.find(person2.getId()).getPassword().equals(person2_updated_pw));
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