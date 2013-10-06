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
    User user1, user2;
    
    @Before
    public void before() {
        user1 = new User("User1");
        user2 = new User("User2");
    }
    
    @Test
    public void testUser() {
        Logger.getAnonymousLogger().log(Level.INFO, "Two users created with attributes: {0} and {1}", new Object[]{user1, user2});
        
        //Two users with different Id's should not be equal
        assertFalse(user1.equals(user2));
        assertFalse(user2.equals(user1));
        
        //New user with same Id as user1. Though they have different names, user1 and 3 should now be equal
        User user3 = new User(user1.getId(), "User3");
        assertTrue(user3.equals(user1));
        assertTrue(user1.equals(user3));
        
        //Change name
        user3.setName("urban");
        assertTrue(user3.getName().equals("urban"));
    }
    
}
