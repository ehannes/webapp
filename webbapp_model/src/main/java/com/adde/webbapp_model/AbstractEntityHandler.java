/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

import java.util.LinkedList;
import java.util.List;

/**
 * An Abstract Entity Container / Handler
 * @author Gustav
 */
public abstract class AbstractEntityHandler<T extends AbstractEntity> {
/*
*List<T> items
+Abstract create(long authorId, String msg)
+T find(long id)
+List<T> getAll()
+void delete(long id)
+void update(T item)
*/
    List<T> items;
    
    public AbstractEntityHandler(){
        items = new LinkedList<T>();
    }
    
    /*
    public Post create(long authorId, String msg){
        Post p = new Post(authorId, msg);
        
        return p;
    }
    */
    
    public List<T> getAll(){
        return items;
    }
    
    //Will be much more efficient with database instead of list...
    public void delete(long id){
        for(T t : items){
            if(t.getId() == id){
                items.remove(t);
                break;
            }
        }
    }
    
    //Much esier with HashMap... how do efficiently?
    //Suggestion: just get and then set the attributes desired...
    public void update(T item){
    }
    
}
