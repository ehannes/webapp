package com.adde.webbapp.model.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class WallPost extends Post {
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Post> comments;

    //To satisfy requirements of @Entity annotation. Don't ever use it!
    public WallPost() {
        this(null, null);
    }
    
    public WallPost(Person author, String msg){
        super(author, msg);
    }

    public List<Post> getComments() {
        return comments;
    }

    public void setComments(List<Post> comments) {
        this.comments = comments;
    }
}