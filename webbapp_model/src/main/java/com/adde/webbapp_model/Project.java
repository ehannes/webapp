package com.adde.webbapp_model;

import com.adde.webbapp_model_dao.ArticleDAO;
import com.adde.webbapp_model_dao.WallPostDAO;
import com.adde.webbapp_model_dao.TodoPostDAO;
import com.adde.webbapp_model_util.AbstractEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 *
 * @author Joakim Danielsson
 */
@Entity
public class Project extends AbstractEntity implements Serializable {

    private String name;
    
    @Temporal(TemporalType.DATE)
    private GregorianCalendar calendar;  
   
    @ManyToOne
    private Person admin;
    
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Person> collaborators;
    
    public Project(){
    }
    
    public Project(String name, Person admin) {
        this.name = name;
        this.admin = admin;
        this.calendar = new GregorianCalendar();
        this.collaborators = new ArrayList<>();
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

//    public List<TodoPost> getMilestonePosts() {
//        return milestonePosts;
//    }
//
    public List<TodoPost> getTodoPosts() {
        return TodoPostDAO.newInstance().getAll();
    }

    public List<Article> getArticles() {
        return ArticleDAO.newInstance().getAll();
    }

    public List<WallPost> getWallPosts() {
        return WallPostDAO.newInstance().getAll();
    }

    public void addCollaborator(Person user) throws Exception {
        if (user.equals(admin)) {
            throw new Exception("The user is Admin");
        } else {
            collaborators.add(user);
        }
    }
//
//    public void createMilestonePost(Person author, String msg) {
//        milestonePosts.add(new TodoPost(this, author, msg));
//    }
//
    public void createTodoPost(Person author, String msg) {
        TodoPostDAO.newInstance().add(new TodoPost(this, author, msg));
    }

    public void createArticle(Person author, String content, String title) {
        ArticleDAO.newInstance().add(new Article(author, content, title));
    }

    public void createWallPost(Person author, String msg) {
        WallPostDAO.newInstance().add(new WallPost(this, author, msg));
    }

    public void deleteCollaborator(Person u) {
        collaborators.remove(u);
    }

    public void deleteArticle(Article a) {
        ArticleDAO.newInstance().remove(a.getId());
    }
    
//    public void deleteMilestonePost(TodoPost m) {
//        milestonePosts.remove(m);
//    }

    public void deleteTodoPost(TodoPost d) {
        TodoPostDAO.newInstance().remove(d.getId());
    }

    public void deleteWallPost(WallPost w) {
        WallPostDAO.newInstance().remove(w.getId());
    }

    @Override
    public String toString() {
        return "Project{Name: " + name + "Admin: " + admin + " Id: " + super.toString() +
                " date created " + calendarToString(calendar) + "}";
    }
}