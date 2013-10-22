
package com.adde.webbapp.frontend.rest;

import com.adde.webbapp.model.entity.Post;
import com.adde.webbapp.model.entity.WallPost;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "wallPosts")
@XmlType(name = "WallPostType")
public class WallPostProxy {

    private WallPost wallPost;

    protected WallPostProxy() {
    }

    public WallPostProxy(WallPost wallPost) {
        this.wallPost = wallPost;
    }

    @XmlElement(required = true)
    public Long getId() {
        return wallPost.getId();
    }

    @XmlElement(required = true)
    public String getMsg() {
        return wallPost.getMsg();
    }

    @XmlElement(required = true)
    public List<Post> getComments() {
        return wallPost.getComments();
    }

    @XmlElement(required = true)
    public Calendar getTimeCreated() {
        return wallPost.getTimeCreated();
    }

    @XmlElement(required = true)
    public Calendar getTimeModified() {
        return wallPost.getTimeModified();
    }
}
