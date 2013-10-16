/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.TodoPost;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class TodoPostDAO extends AbstractDAO<TodoPost,Long>{
    
    private TodoPostDAO(){
        super(TodoPost.class);
    }
    
    public static TodoPostDAO newInstance(){
        return new TodoPostDAO();
    }

    @Override
    public void add(TodoPost tp){
        Calendar c = new GregorianCalendar();
        tp.setDateCreated(c);
        tp.setDateModified(c);
        super.add(tp);
    }
    
    /*public List<TodoPost> getTodoPostsByProject(Project p){
        List<TodoPost> result = new LinkedList<>();
        
        return result;
    }*/
    
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