package com.adde.webbapp_model;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class WallPost extends Post<Project> {
    @OneToMany(cascade = {CascadeType.ALL})
    private LinkedList<Comment> comments;

    //To satisfy requirements of @Entity annotation. Don't ever use it!
    public WallPost() {
        this(null, null, null);
    }
    
    public WallPost(Project context, Person author, String msg){
        super(context, author, msg);
        comments = new LinkedList<>();
    }
    
    public List<Comment> getComments(){
        return comments;
    }
    
    public void addComment(Person author, String msg){
        comments.add(new Comment(this, author, msg));
    }
    
    public void removeComment(Comment comment){
        comments.remove(comment);
    }
    
    public void removeCommentsByUser(Person user) {
        for(Comment comment : comments){
            if(comment.getAuthor().equals(user)){
                comments.remove(comment);
            }
        }
    }
    
    @Override
    public String toString(){
        return "WallPost{id: " + getId() + ", author: " + getAuthor()
                + ", msg: " + getMsg() + ", dateCreated: "
                + getStringDateCreated() + ", dateModified: "
                + getStringDateModified() + ", comments: "
                + comments.toString();
    }
}
