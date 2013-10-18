package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.Post;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class PostCatalogue extends AbstractDAO<Post, Long> {

    private PostCatalogue() {
        super(Post.class);
    }

    public static PostCatalogue newInstance() {
        return new PostCatalogue();
    }

    @Override
    public void add(Post p) {
        if (p.getId() == null) {
            GregorianCalendar c = new GregorianCalendar();
            p.setTimeCreated(c);
            p.setTimeModified(c);
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
        p.setTimeModified(new GregorianCalendar());
        super.update(p);
    }
}
