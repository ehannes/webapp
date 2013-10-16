/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import com.adde.webbapp_model_util.AbstractDAO;
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
    
    public List<WallPost> getWallPostByProject(Project p){
        List<WallPost> result = new LinkedList<>();
        for(WallPost wp : getAll()){
            if(wp.getContext().equals(p)){
                result.add(wp);
            }
        }
        return result;
    }
}