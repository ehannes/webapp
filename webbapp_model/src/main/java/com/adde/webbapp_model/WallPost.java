package com.adde.webbapp_model;

import java.util.List;

public class WallPost extends Post {
    List<Post> comments;
    
    /**
     * @param author Author
     * @param msg Message
     */
    public WallPost(User author, String msg){
        super(author, msg);
    }
    
    public List<Post> getComments(){
        return comments;
    }
    
    public void addComment(User author, String msg){
        comments.add(new Post(author, msg));
    }
    
}
