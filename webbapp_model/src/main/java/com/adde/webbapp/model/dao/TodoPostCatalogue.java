package com.adde.webbapp.model.dao;

import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.TodoPost;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class TodoPostCatalogue extends AbstractDAO<TodoPost, Long> {

    private TodoPostCatalogue() {
        super(TodoPost.class);
    }

    public static TodoPostCatalogue newInstance() {
        return new TodoPostCatalogue();
    }

    @Override
    public void add(TodoPost tp) {
        Calendar c = new GregorianCalendar();
        tp.setTimeCreated(c);
        tp.setTimeModified(c);
        super.add(tp);
    }

    @Override
    public TodoPost update(TodoPost tp) {
        //don't allow assigning duplicates
        if (tp.getAssignedTo() != null) {
            List<Person> assignedTo = new LinkedList<>();
            for (Person p : tp.getAssignedTo()) {
                if (!assignedTo.contains(p)) {
                    assignedTo.add(p);
                }
            }
            tp.setAssignedTo(assignedTo);
        }
        return super.update(tp);
    }

    public List<TodoPost> getTodoPostsByPerson(Person p) {
        List<TodoPost> result = new LinkedList<>();
        for (TodoPost tp : getAll()) {
            if (tp.getAuthor().equals(p)) {
                result.add(tp);
            }
        }
        return result;
    }
}