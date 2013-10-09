package com.adde.webbapp_model;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for User.
 * @author hannes
 */
public class UserTest {
    private User user1, user2, userWithSetId, nullTest;
    
    @Before
    public void before() {
        user1 = new User("User1", "email1");
        user2 = new User("User2", "email2");
        userWithSetId = new User(15,"UserWithSetId", "email");
        nullTest = new User("newUser", "newUserEmail");
    }
    
    @Test
    public void nullTest() {
        //New user should not have any values set to null
        
        assertTrue(nullTest.getDateCreated() != null);
        assertTrue(nullTest.getEmail() != null);
        assertTrue(nullTest.getFirstName() != null);
        assertTrue(nullTest.getLastName() != null);
        assertTrue(nullTest.getUserName() != null);
    }
    
    @Test
    public void getSetTest() {
        assertTrue(userWithSetId.getId() == 15);
        
        assertTrue(user1.getUserName().equals("User1"));
        
        user1.setFirstName("newName");
        assertTrue(user1.getFirstName().equals("newName"));
        
        user1.setLastName("newLastName");
        assertTrue(user1.getLastName().equals("newLastName"));
        
        user1.setEmail("newEmail");
        assertTrue(user1.getEmail().equals("newEmail"));
    }
    
    @Test
    public void equalityTest() {
        //Two users with different Id's should not be equal
        assertFalse(user1.equals(user2));
        assertFalse(user2.equals(user1));

        assertTrue(user1.equals(user1));
        
        //New user with same Id as user1. Though different nicknames, users should now be equal
        User user3 = new User(user1.getId(), "User3", "email3");
        assertTrue(user3.equals(user1));
        assertTrue(user1.equals(user3));
        
        //Same nickname doesn't mean equal
        User user4 = new User("User1", "email1");
        assertTrue(user4.getUserName().equals(user1.getUserName()));
        assertFalse(user4.equals(user1));
    }
    
    @Test
    public void printUser() {
        Logger.getAnonymousLogger().log(Level.INFO, "Two users created with attributes: {0} and {1}", new Object[]{user1, user2});
    }
}