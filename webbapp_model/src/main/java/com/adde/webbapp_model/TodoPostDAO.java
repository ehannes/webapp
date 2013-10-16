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
    
    public static TodoPostDAO newInstance(){
        return new TodoPostDAO(ProjectPlatform.PU);
    }
    
    public List<TodoPost> getTodoPostsByProject(Project p){
        List<TodoPost> result = new LinkedList<>();
        for(TodoPost tp : getAll()){
            if(tp.getContext().equals(p)){
                result.add(tp);
            }
        }
        return result;
    }
    
    public List<TodoPost> getTodoPostsByPerson(Person p){
        List<TodoPost> result = new LinkedList<>();
        for(TodoPost tp : getAll()){
            if(tp.getAuthor().equals(p)){
                result.add(tp);
            }
        }
        return result;
    }
}