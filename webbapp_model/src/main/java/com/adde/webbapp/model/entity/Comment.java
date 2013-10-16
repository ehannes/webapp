/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends Post<WallPost> {
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private WallPost context;

    //To satisfy requirements of @Entity annotation. Don't ever use it!
    public Comment() {
        this(null,null,null);
    }
    
    public Comment(WallPost context, Person author, String msg){
        super(context, author, msg);
        this.context = context;
    }
    
    @Override
    public String toString(){
        return "Comment{id: " + getId() + ", author: " + getAuthor()
                + ", msg: " + getMsg() + ", dateCreated: "
                + getStringDateCreated() + ", dateModified: "
                + getStringDateModified() + '}';
    }
}