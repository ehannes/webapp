package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Comment;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.WallPost;
import java.util.LinkedList;
import java.util.List;

public class CommentDAO extends AbstractDAO<Comment, Long> {
    
    private CommentDAO(){
        super(Comment.class);
    }
    
    public static CommentDAO newInstance(){
        return new CommentDAO();
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
