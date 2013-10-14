package com.adde.webbapp_model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 *
 *
 * @author Joakim Danielsson
 */
public class Project {

    private String name;
    private final long id;
    private Person admin;
    private List<Person> collaborators;
    private List<TodoPost> todoPosts;
    private List<TodoPost> milestonePosts;
    private List<Article> articles;
    private List<WallPost> wallPosts;
    private GregorianCalendar calendar;

    public Project(String name, Person admin) {
        id = new Long(new Random().nextInt(1000000));
        this.name = name;
        this.admin = admin;
        this.milestonePosts = new ArrayList<>();
        this.collaborators = new ArrayList<>();
        this.todoPosts = new ArrayList<>();
        this.articles = new ArrayList<>();
        this.wallPosts = new ArrayList<>();
        this.calendar = new GregorianCalendar();
    }

    public Project(long id, String name, Person admin) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.milestonePosts = new ArrayList<>();
        this.collaborators = new ArrayList<>();
        this.todoPosts = new ArrayList<>();
        this.articles = new ArrayList<>();
        this.wallPosts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public long getId() {
        return id;
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
        return "Project{Name: " + name + "Admin: " + admin + " Id: " + id +
                " date created " + calendarToString(calendar) + "}";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Project) {
            Project p = (Project) o;
            if (p.getId() == this.id) { //equal if same id
                return true;
            }
        }
        return false;
    }
}
