/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp_model;

/**
 *
 * @author Gustav
 */
public abstract class AbstractEntity {
    long id;
    
    //Generate ID somewhere!!!
    public AbstractEntity(){
    }
    
    public long getId(){
        return id;
    }
}
