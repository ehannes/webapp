/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model_dao;

import com.adde.webbapp_model.Person;
import com.adde.webbapp_model.Project;
import com.adde.webbapp_model.TodoPost;
import com.adde.webbapp_model_util.AbstractDAO;
import java.util.LinkedList;
import java.util.List;

public class TodoPostDAO extends AbstractDAO<TodoPost,Long>{
    
    private TodoPostDAO(){
        super(TodoPost.class);
    }
    
    public static TodoPostDAO newInstance(){
        return new TodoPostDAO();
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