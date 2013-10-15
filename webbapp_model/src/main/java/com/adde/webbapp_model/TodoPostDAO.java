/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import com.adde.webbapp_model_util.AbstractDAO;
import java.util.LinkedList;
import java.util.List;

public class TodoPostDAO extends AbstractDAO<TodoPost,Long>{
    
    private TodoPostDAO(String puName){
        super(TodoPost.class, puName);
    }
    
    public static TodoPostDAO newInstance(String puName){
        return new TodoPostDAO(puName);
    }
    
    public List<TodoPost> getWallPostByProject(Project p){
        List<TodoPost> result = new LinkedList<>();
        for(TodoPost tp : getRange(0, getCount()-1)){
            if(tp.getContext().equals(p)){
                result.add(tp);
            }
        }
        return result;
    }
}