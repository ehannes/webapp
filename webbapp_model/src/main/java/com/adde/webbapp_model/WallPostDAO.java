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
    
    private WallPostDAO(String puName){
        super(WallPost.class, puName);
    }
    
    public static WallPostDAO newInstance(String puName) {
        return new WallPostDAO(puName);
    }
    
    public List<WallPost> getWallPostByProject(Project p){
        List<WallPost> result = new LinkedList<>();
        for(WallPost wp : getRange(0, getCount()-1)){
            if(wp.getContext().equals(p)){
                result.add(wp);
            }
        }
        return result;
    }
}