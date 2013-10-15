/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import com.adde.webbapp_model_util.AbstractDAO;
import java.util.LinkedList;
import java.util.List;


//use to fetch comments on wallposts from database.
public class PostDAO extends AbstractDAO<Post,Long> {
    
    public PostDAO(String puName){
        super(Post.class, puName);
    }
    
    /*
    public List<Post> getPostByWallPost(WallPost wp){
        List<Post> result = new LinkedList<>();
        for(Post p : getRange(0, getCount()-1)){
            if(wp.equals(p.getWallPost())){
                result.add(p);
            }
        }
        return result;
    }
    */
}
