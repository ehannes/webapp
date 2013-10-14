package com.adde.webbapp_model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 *
 * @author Joakim Danielsson
 */
@Entity
public class Project extends AbstractEntity {

    private String name;
    
    @OneToMany
    private Person admin;
    
    private List<Person> collaborators;
    private List<TodoPost> todoPosts;
    private List<TodoPost> milestonePosts;
    private List<Article> articles;
    private List<WallPost> wallPosts;
    private GregorianCalendar calendar;

    public Project(){
    }
    
    public Project(String name, Person admin) {
        this.name = name;
        this.admin = admin;
        this.milestonePosts = new ArrayList<>();
        this.collaborators = new ArrayList<>();
        this.todoPosts = new ArrayList<>();
        this.articles = new ArrayList<>();
        this.wallPosts = new ArrayList<>();
        this.calendar = new GregorianCalendar();
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setAdmin(Person currentAdmin, Person newAdmin) throws Exception {
        if (currentAdmin.equals(admin)) {
            admin = newAdmin;
            collaborators.add(admin);
            collaborators.remove(newAdmin);
        }
        throw new Exception("Current admin in setAdmin was incorrect");
    }

    public Person getAdmin() {
        return admin;
    }

    public GregorianCalendar getDateCreated() {
        return calendar;
    }

    public String calendarToString(GregorianCalendar c) {
        return  c.get(GregorianCalendar.DAY_OF_MONTH)
                + "/" + (c.get(GregorianCalendar.MONTH) + 1)
                + " " + c.get(GregorianCalendar.YEAR);
    }

    public List<Person> getCollaborators() {
        return collaborators;
    }

    public List<TodoPost> getMilestonePosts() {
        return milestonePosts;
    }

    public List<TodoPost> getTodoPosts() {
        return todoPosts;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<WallPost> getWallPosts() {
        return wallPosts;
    }

    public void addCollaborator(Person user) throws Exception {
        if (user.equals(admin)) {
            throw new Exception("The user is Admin");
        } else {
            collaborators.add(user);
        }
    }

    public void createMilestonePost(Person author, String msg) {
        milestonePosts.add(new TodoPost(author, msg));
    }

    public void createTodoPost(Person author, String msg) {
        todoPosts.add(new TodoPost(author, msg));
    }

    public void createArticle(Person author, String content, String title) {
        articles.add(new Article(author, content, title));
    }

    public void createWallPost(Person author, String msg) {
        wallPosts.add(new WallPost(author, msg));
    }

    public void deleteCollaborator(Person u) {
        collaborators.remove(u);
    }

    public void deleteArticle(Article a) {
        articles.remove(a);
    }

    public void deleteMilestonePost(TodoPost m) {
        milestonePosts.remove(m);
    }

    public void deleteTodoPost(TodoPost d) {
        todoPosts.remove(d);
    }

    public void deleteWallPost(WallPost w) {
        wallPosts.remove(w);
    }

    @Override
    public String toString() {
        return "Project{Name: " + name + "Admin: " + admin + " Id: " + super.toString() +
                " date created " + calendarToString(calendar) + "}";
    }
}
