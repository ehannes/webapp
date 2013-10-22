
package com.adde.webbapp.frontend.rest;

import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.Post;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "Post")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "PostType")
public class PostProxy {

    private Post post;

    protected PostProxy() { }
   
    public PostProxy(Post post) { 
        this.post = post;
    }
    
    @XmlElement(required = true)
    public Long getId() {
        return post.getId();
    }
    
    @XmlElement (required = true)
    public String getMsg() {
        return post.getMsg();
    }

    @XmlElement (required=true)
    public Calendar getTimeCreated(){
        return post.getTimeCreated();
    }
    
    @XmlElement (required=true)
    public Calendar getTimeModified(){
        return post.getTimeModified();
    }
    
    @XmlElement (required=true)
    public Person getAuthor(){
        return post.getAuthor();
    }
}
