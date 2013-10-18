package com.adde.webbapp.model;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.Post;
import com.adde.webbapp.model.entity.TodoPost;
import com.adde.webbapp.model.entity.WallPost;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class PostsTest {

    private Person person1;
    private Person person2;
    private Post post1;
    private Post post2;
    private WallPost wallPost1;
    private TodoPost todoPost1;
    private Calendar cal1;
    private static final String STR1 = "Content";
    private static final String STR2 = "EditedContent";
    private DAOFactory daos;

    @Before
    public void buildup() {
        daos = DAOFactory.getDAOFactory();

        person1 = new Person("person1", "email1", "user1pw");
        person2 = new Person("person2", "email2", "user2pw");

        daos.getPersonCatalogue().add(person1);
        daos.getPersonCatalogue().add(person2);

        post1 = new Post(person1, STR1);
        post2 = new Post(person2, STR2);
    }

    @After
    public void teardown() {
        assertTrue(daos.getProjectCatalogue().getCount() == 0);
        assertTrue(daos.getWallPostCatalogue().getCount() == 0);
        assertTrue(daos.getTodoPostCatalogue().getCount() == 0);
        assertTrue(daos.getPostCatalogue().getCount() == 0);

        daos.getPersonCatalogue().remove(person1.getId());
        daos.getPersonCatalogue().remove(person2.getId());
    }

    @Test
    public void PostTest() {
        daos.getPostCatalogue().add(post1);
        daos.getPostCatalogue().add(post2);
        cal1 = post1.getTimeCreated();
        assertFalse(cal1 == null);

        //persistence tests
        Post post3 = new Post(person1, STR1);
        assertTrue(post3.getId() == null);
        assertTrue(daos.getPostCatalogue().getCount() == 2);
        daos.getPostCatalogue().add(post3);
        assertFalse(post3.getId() == null);
        assertTrue(daos.getPostCatalogue().getCount() == 3);

        //removal from DB
        daos.getPostCatalogue().remove(post3.getId());
        assertTrue(daos.getPostCatalogue().getCount() == 2);

        //persistance of duplicates test
        int countBefore = daos.getPostCatalogue().getCount();
        daos.getPostCatalogue().add(post1); //duplicate! shouldn't be added
        assertTrue(daos.getPostCatalogue().getCount() == countBefore);

        //basic tests
        assertTrue(post1.equals(post1));
        assertFalse(post1.equals(post2));
        assertTrue(post1.getAuthor().equals(person1));
        assertFalse(post1.getTimeCreated() == null);
        assertTrue(post1.getTimeCreated().equals(cal1));
        assertTrue(post1.getTimeCreated().equals(post1.getTimeModified()));
        assertTrue(post1.getMsg().equals(STR1));
        try {
            //Date modified test (got to wait 1ms)
            Thread.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getAnonymousLogger().log(Level.INFO, "PostsTest: "
                    + "Interrupted on Thread.sleep");
            System.exit(1);
        }
        daos.getPostCatalogue().setMsg(post1.getId(), STR2);
        assertTrue(daos.getPostCatalogue().find(post1.getId()).getMsg().equals(STR2));
        post1 = daos.getPostCatalogue().find(post1.getId());
        assertTrue(post1.getTimeCreated().equals(cal1));
        assertFalse(post1.getTimeModified().equals(cal1));

        daos.getPostCatalogue().remove(post1.getId());
        daos.getPostCatalogue().remove(post2.getId());
    }

    //Depends on previous test!!
    @Test
    public void WallPostTest() {
        int initNrOfPostsInDB = daos.getPostCatalogue().getCount();
        wallPost1 = new WallPost(person1, STR1);

        //init values
        List<Post> commentsTmp = wallPost1.getComments();
        assertTrue(commentsTmp == null);

        //persist, should get an id
        assertTrue(wallPost1.getId() == null);
        daos.getWallPostCatalogue().add(wallPost1);
        assertFalse(wallPost1.getId() == null);

        //adding comments
        commentsTmp = new LinkedList<>();
        daos.getPostCatalogue().add(post1);
        daos.getPostCatalogue().add(post2);
        assertFalse(daos.getPostCatalogue().find(post1.getId()) == null);
        assertFalse(daos.getPostCatalogue().find(post2.getId()) == null);
        commentsTmp.add(post1);
        commentsTmp.add(post2);
        wallPost1.setComments(commentsTmp);
        wallPost1 = daos.getWallPostCatalogue().update(wallPost1);
        commentsTmp = wallPost1.getComments();
        //Bug: Netbeans thinks commentsTmp is null two lines below if the line
        //below is assertFalse(commentsTmp == null).
        assertTrue(commentsTmp != null);
        assertTrue(commentsTmp.size() == 2);

        //duplicates test
        commentsTmp.add(commentsTmp.get(0));
        wallPost1.setComments(commentsTmp);
        wallPost1 = daos.getWallPostCatalogue().update(wallPost1);
        assertFalse(wallPost1.getComments() == null);
        assertTrue(wallPost1.getComments().size() == commentsTmp.size() - 1);

        //persist.remove working?
        daos.getWallPostCatalogue().remove(wallPost1.getId());
        assertTrue(daos.getWallPostCatalogue().getCount() == 0);
        assertTrue(daos.getPostCatalogue().getCount() == initNrOfPostsInDB);
    }

    @Test
    public void TodoPostTest() {
        //storage in database
        todoPost1 = new TodoPost(person1, STR1);
        assertTrue(todoPost1.getId() == null);
        daos.getTodoPostCatalogue().add(todoPost1);
        assertFalse(todoPost1.getId() == null);
        cal1 = todoPost1.getTimeCreated();

        //initial values
        assertTrue(todoPost1.getAssignedTo().isEmpty());
        assertTrue(todoPost1.getDeadline() == null);
        assertTrue(todoPost1.getPriority() == null);

        //set priority
        todoPost1.setPriority(TodoPost.Priority.LOW);
        assertTrue(todoPost1.getPriority().equals(TodoPost.Priority.LOW));

        //add
        List<Person> list = new LinkedList<>();
        list.add(person1);
        list.add(person2);
        todoPost1.setAssignedTo(list);
        assertFalse(todoPost1.getAssignedTo() == null);
        assertTrue(todoPost1.getAssignedTo().size() == 2);

        //duplicates
        list.add(person1);
        todoPost1.setAssignedTo(list);
        todoPost1 = daos.getTodoPostCatalogue().update(todoPost1);
        assertTrue(todoPost1.getAssignedTo().size() == 2);

        //deadline
        todoPost1.setDeadline(cal1);
        assertTrue(todoPost1.getDeadline().equals(cal1));
        
        //remove from DB
        daos.getTodoPostCatalogue().remove(todoPost1.getId());
    }
}