package com.adde.webapp_model;

import java.util.ArrayList;
import java.util.Date;
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
    private final Date dateCreated;
    private User admin;
    private List<User> collaborators;
    private List<TodoPost> todoPosts;
    private List<TodoPost> milestonePosts;
    private List<Article> articles;
    private List<WallPost> wallPosts;

    public Project(String name, User admin) {
        id = new Long(new Random().nextInt(1000000));
        this.name = name;
        this.dateCreated = new Date();
        this.admin = admin;
        this.milestonePosts = new ArrayList<TodoPost>();
        this.collaborators = new ArrayList<User>();
        this.todoPosts = new ArrayList<TodoPost>();
        this.articles = new ArrayList<Article>();
        this.wallPosts = new ArrayList<WallPost>();
    }

    public Project(long id, String name, User admin) {
        this.id = id;
        this.name = name;
        this.dateCreated = new Date();
        this.admin = admin;
        this.milestonePosts = new ArrayList<TodoPost>();
        this.collaborators = new ArrayList<User>();
        this.todoPosts = new ArrayList<TodoPost>();
        this.articles = new ArrayList<Article>();
        this.wallPosts = new ArrayList<WallPost>();
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

    public void setAdmin(User currentAdmin, User newAdmin) throws Exception {
        if (currentAdmin.equals(admin)) {
            admin = newAdmin;
            collaborators.add(admin);
            collaborators.remove(newAdmin);
        }
        throw new Exception("Current admin in setAdmin was incorrect");
    }

    public User getAdmin() {
        return admin;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public List<User> getCollaborators() {
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

    public void addCollaborator(User user) throws Exception {
        if (user.equals(admin)) {
            throw new Exception("The user is Admin");
        } else {
            collaborators.add(user);
        }
    }

    public void createMilestonePost(User author, String msg) {
        milestonePosts.add(new TodoPost(author, msg));
    }

    public void createTodoPost(User author, String msg) {
        todoPosts.add(new TodoPost(author, msg));
    }

    public void createArticle(User author, String content, String title) {
        articles.add(new Article(author, content, title));
    }

    public void createWallPost(User author, String msg) {
        wallPosts.add(new WallPost(author, msg));
    }

    public void deleteCollaborator(User u) {
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
        return "Project{Name: " + name + "Admin: " + admin + "Id: " + id + "}";
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