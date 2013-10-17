package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.Post;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostDAO extends AbstractDAO<Post, Long> {

    private PostDAO() {
        super(Post.class);
    }

    public static PostDAO newInstance() {
        return new PostDAO();
    }

    @Override
    public void add(Post p) {
        if (p.getId() == null) {
            GregorianCalendar c = new GregorianCalendar();
            p.setDateCreated(c);
            p.setDateModified(c);
            super.add(p);
        }
    }

    public void add(Person author, String msg) {
        add(new Post(author, msg));
    }

    /*public List<Post> getByWallPost(WallPost wp){
     List<Post> result = new LinkedList<>();
     return result;
     }*/
    public List<Post> getByAuthor(Person p) {
        List<Post> result = new LinkedList<>();
        for (Post post : getAll()) {
            if (p.equals(post.getAuthor())) {
                result.add(post);
            }
        }
        return result;
    }

    public void setMsg(Long id, String msg) {
        Post p = super.find(id);
        p.setMsg(msg);
        p.setDateModified(new GregorianCalendar());
        super.update(p);
    }
}
