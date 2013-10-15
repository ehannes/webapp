package com.adde.webbapp_model;

import java.util.GregorianCalendar;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PostsTest {/*
    private Person person1;
    private Person person2;
    private Comment comment1;
    private Comment comment2;
    private WallPost wallPost1;
    private WallPost wallPost2;
    private TodoPost todoPost1;
    private TodoPost todoPost2;
    private GregorianCalendar time1;
    private Project project1;
    private Project project2;
    
    private static final String STR1 = "Content";
    private static final String STR2 = "EditedContent";
    
    @Before
    public void setup(){
        person1 = new Person("person1", "email1");
        person2 = new Person("person2", "email2");
        time1 = new GregorianCalendar();
        project1 = new Project();
        project2 = new Project();
        todoPost1 = new TodoPost(project1, person1, STR1);
        todoPost2 = new TodoPost(project2, person2, STR2);
        wallPost1 = new WallPost(project1, person1, STR1);
        wallPost2 = new WallPost(project2, person2, STR2);
        comment1 = new Comment(wallPost1, person1, STR1);
        comment2 = new Comment(wallPost2, person2, STR2);
    }
    
    @Test
    public void CommentTest(){
        assertTrue(comment1.equals(comment1));
        String tmp1 = comment1.toString();
        GregorianCalendar cal1 = comment1.getDateCreated();
        GregorianCalendar cal2 = comment2.getDateModified();
        assertTrue(cal1.equals(cal2));
        try{
            Thread.sleep(1);
        } catch(InterruptedException e){
            System.out.println("PostsTest.CommentTest: "
                    + "Interrupted on Thread.sleep");
        }
        comment1.setMsg(STR2);
        assertTrue(comment1.getMsg().equals(STR2));
        assertFalse(tmp1.equals(comment1.getMsg()));
        assertFalse(cal1.equals(comment1.getDateModified()));
        assertFalse(cal2.equals(comment1.getDateModified()));
        assertTrue(cal1.equals(comment1.getDateCreated()));
        
    }*/
}