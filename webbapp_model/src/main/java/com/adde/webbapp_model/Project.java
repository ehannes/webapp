/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 *update delete!
 *
 * @author Joakim Danielsson
 */
public class Project {

    private String name;
    private Long id;
    private List<User> users = new ArrayList<User>();
    private List<DeadlinePost> deadlinePosts = new ArrayList<DeadlinePost>();
    private List<MilestonePost> milestonePosts = new ArrayList<MilestonePost>();
    private List<Article> articles = new ArrayList<Article>();
    private List<WallPost> wallPosts = new ArrayList<WallPost>();

    public Project(String name) {
        id = new Long(new Random().nextInt(1000));
        this.name = name;
    }

    public Project(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public List<User> getUsers() {
        return users;
    }

    public List<DeadlinePost> getDeadlinePosts() {
        return deadlinePosts;
    }

    public List<MilestonePost> getMilestonePosts() {
        return milestonePosts;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<WallPost> getWallPosts() {
        return wallPosts;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addDeadlinePost(User author, User responsibleUser, String msg,
            Date deadline, int priority) {
        deadlinePosts.add(new DeadlinePost(author, responsibleUser, msg,
                deadline, priority));
    }

    public void addMilestonePost(User author, User responsibleUser, String msg,
            Date deadline, int priority, List<User> assignedTo) {
        milestonePosts.add(new MilestonePost(author, responsibleUser, msg,
                deadline, priority, assignedTo));
    }

    public void addArticle(User author, String content) {
        articles.add(new Article(author, content));
    }

    public void addWallPost(User author, String msg) {
        wallPosts.add(new WallPost(author, msg));
    }

    public void deleteUser(Long id) {
        for (int i = users.size() - 1; i > -1; i--) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
            }
        }
    }

    public void deleteArticle(Long id) {
        for (int i = articles.size() - 1; i > -1; i--) {
            if (articles.get(i).getId().equals(id)) {
                articles.remove(i);
            }
        }
    }

    public void deleteDeadlinePost(Long id) {
        for (int i = deadlinePosts.size() - 1; i > -1; i--) {
            if (deadlinePosts.get(i).getId().equals(id)) {
                deadlinePosts.remove(i);
            }
        }
    }

    public void deleteMilestonePost(Long id) {
        for (int i = milestonePosts.size() - 1; i > -1; i--) {
            if (milestonePosts.get(i).getId().equals(id)) {
                milestonePosts.remove(i);
            }
        }
    }

    public void deleteWallPost(Long id) {
        for (int i = wallPosts.size() - 1; i > -1; i--) {
            if (wallPosts.get(i).getId().equals(id)) {
                wallPosts.remove(i);
            }
        }
    }
    
    @Override
    public String toString() {
        return "Project{Name: " + name + " Id: " + id;
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
