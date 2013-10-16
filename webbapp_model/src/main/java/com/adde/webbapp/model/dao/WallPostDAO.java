package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.WallPost;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


//use to fetch comments on wallposts from database.
public class WallPostDAO extends AbstractDAO<WallPost,Long> {
    
    private WallPostDAO(){
        super(WallPost.class);
    }
    
    public static WallPostDAO newInstance() {
        return new WallPostDAO();
    }

    @Override
    public void add(WallPost wp){
        Calendar c = new GregorianCalendar();
        wp.setDateCreated(c);
        wp.setDateModified(c);
        super.add(wp);
    }
    
    /*public List<WallPost> getWallPostByProject(Project p){
        List<WallPost> result = new LinkedList<>();
        
        return result;
    }*/
    
    public List<WallPost> getByPerson(Person p){
        List<WallPost> result = new LinkedList<>();
        for(WallPost wp : getAll()){
            if(p.equals(wp.getAuthor())){
                result.add(wp);
            }
        }
        return result;
    }
}