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
    
    public void addComment(User author, String msg){
        comments.add(new Post(author, msg));
    }
    
    public void addComment(Post comment){
        comments.add(comment);
    }
    
    public void removeComment(Post comment){
        comments.remove(comment);
    }
    
    public void removeCommentsByUser(User user) {
        for(Post comment : comments){
            if(comment.getAuthor().equals(user)){
                comments.remove(comment);
            }
        }
    }
    
    @Override
    public String toString(){
        return "WallPost{id=" + getId() + ", author=" + getAuthor() + ", msg=" + getMsg()
                + ", dateCreated=" + getStringDateCreated() + ", dateModified="
                + getStringDateModified() + ", comments=" + comments.toString();
    }
}
