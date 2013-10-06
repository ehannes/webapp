package com.adde.webbapp_model;

import java.util.LinkedList;
import java.util.List;

public class WallPost extends Post {
    private LinkedList<Post> comments;
    
    public WallPost(User author, String msg){
        super(author, msg);
        comments = new LinkedList<Post>();
    }
    
    public List<Post> getComments(){
        return comments;
    }
    
    public boolean addComment(User author, String msg){
        return comments.add(new Post(author, msg));
    }
    
    public boolean addComment(Post comment){
        return comments.add(comment);
    }
    
    public boolean removeComment(Post comment){
        return comments.remove(comment);
    }
    
    public boolean removeCommentsByUser(User user) {
        boolean result = true;
        for(Post comment : comments){
            if(comment.getAuthor().equals(user)){
                result = result && comments.remove(comment);
            }
        }
        return result;
    }
    
    @Override
    public String toString(){
        return "WallPost{author=" + getAuthor() + ", msg=" + getMsg()
                + ", dateCreated=" + getDateCreated() + ", dateModified="
                + getDateModified() + ", comments=" + comments.toString();
    }
    
    @Override
    public boolean equals(Object o){
        boolean result = false;
        if(o instanceof WallPost){
            WallPost o2 = (WallPost) o;
            result = super.equals(o) && o2.getComments() != null &&
                    o2.getComments().equals(comments);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = super.hashCode() + 47 * hash + (this.comments != null ? this.comments.hashCode() : 0);
        return hash;
    }
}
