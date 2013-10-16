package com.adde.webbapp_model;

import com.adde.webbapp_model_util.AbstractDAO;
import java.util.LinkedList;
import java.util.List;

public class CommentDAO extends AbstractDAO<Comment, Long> {
    
    private CommentDAO(String puName){
        super(Comment.class, puName);
    }
    
    public static CommentDAO newInstance(){
        return new CommentDAO(ProjectPlatform.PU);
    }
    
    public List<Comment> getCommentsByWallPost(WallPost wp){
        List<Comment> result = new LinkedList<>();
        for(Comment c : getAll()){
            if(c.getContext().equals(wp)){
                result.add(c);
            }
        }
        return result;
    }
    
    public List<Comment> getCommentsByPerson(Person p){
        List<Comment> result = new LinkedList<>();
        for(Comment c : getAll()){
            if(c.getAuthor().equals(p)){
                result.add(c);
            }
        }
        return result;
    }
}
