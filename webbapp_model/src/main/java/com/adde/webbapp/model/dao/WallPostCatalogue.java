package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.Post;
import com.adde.webbapp.model.entity.WallPost;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class WallPostCatalogue extends AbstractDAO<WallPost, Long> {

    private WallPostCatalogue() {
        super(WallPost.class);
    }

    public static WallPostCatalogue newInstance() {
        return new WallPostCatalogue();
    }

    @Override
    public void add(WallPost wp) {
        Calendar c = new GregorianCalendar();
        wp.setTimeCreated(c);
        wp.setTimeModified(c);
        super.add(wp);
    }
    
    public List<WallPost> getByPerson(Person p) {
        List<WallPost> result = new LinkedList<>();
        for (WallPost wp : getAll()) {
            if (p.equals(wp.getAuthor())) {
                result.add(wp);
            }
        }
        return result;
    }

    @Override
    public WallPost update(WallPost wp) {
        //Don't allow duplicate comments (with same id)
        if (wp.getComments() != null) {
            List<Post> comments = new LinkedList<>();
            for (Post comment : wp.getComments()) {
                if (!comments.contains(comment)) {
                    comments.add(comment);
                }
            }
            wp.setComments(comments);
        }
        return super.update(wp);
    }
}