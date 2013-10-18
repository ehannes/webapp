/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.frontend.rest;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.TodoPostCatalogue;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.Project;
import com.adde.webbapp.model.entity.TodoPost;
import java.net.URI;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("projects/{projectId}/todo")
public class TodoPostCatalogueResource {

    private final TodoPostCatalogue todoPostCatalogue =
            DAOFactory.getDAOFactory().getTodoPostCatalogue();
    @Context
    private UriInfo uriInfo;
    @Context
    private HttpServletRequest request;

    private boolean allowed(Long projectId) {
        return true;
//        HttpSession session = request.getSession(true);
//        Person person = (Person) session.getAttribute("person");
//        Project project = DAOFactory.getDAOFactory().getProjectCatalogue().find(projectId);
//        return project != null && (person.equals(project.getAdmin())
//                || project.getCollaborators().contains(person));
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response add(
            @FormParam("msg") String msg,
            @PathParam("projectId") Long projectId) {

        if (!allowed(projectId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Person author = (Person) request.getSession().getAttribute("person");
        TodoPost todoPost = new TodoPost(author, msg);
        todoPostCatalogue.add(todoPost);

        // Tell client where new resource is (URI to)
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(todoPost.getId())).build(todoPost);
        return Response.created(uri).build();
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll(@PathParam("projectId") Long projectId) {

        if (!allowed(projectId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        List<TodoPostProxy> result = new LinkedList<>();
        for (TodoPost p : todoPostCatalogue.getAll()) {
            result.add(new TodoPostProxy(p));
        }
        GenericEntity ge = new GenericEntity<List<TodoPostProxy>>(result) {
        };
        return Response.ok(ge).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getRange(@PathParam("projectId") Long projectId,
            @QueryParam("first") int first,
            @QueryParam("nItems") int nItems) {

        if (!allowed(projectId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        if (first < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else if (first >= todoPostCatalogue.getCount()) {
            return Response.noContent().build();
        }

        List<TodoPostProxy> result = new LinkedList<>();
        nItems = Math.min(first + nItems - 1, todoPostCatalogue.getCount());
        for (TodoPost p : todoPostCatalogue.getRange(first, nItems)) {
            result.add(new TodoPostProxy(p));
        }
        GenericEntity ge = new GenericEntity<List<TodoPostProxy>>(result) {
        };
        return Response.ok(ge).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(
            @PathParam("projectId") Long projectId,
            @PathParam("id") long id) {

        if (!allowed(projectId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        TodoPost todoPost = todoPostCatalogue.find(id);
        if (todoPost == null) {
            return Response.noContent().build();
        }
        return Response.ok(new PostProxy(todoPost)).build();
    }

    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCount(@PathParam("projectId") Long projectId) {
        if (!allowed(projectId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(new PrimitiveJSONWrapper<>(todoPostCatalogue.getCount())).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response remove(
            @PathParam("projectId") Long projectId,
            @PathParam("id") long id) {

        Person person = (Person) request.getSession().getAttribute("person");
        Project project = DAOFactory.getDAOFactory().getProjectCatalogue().find(projectId);
        TodoPost todoPost = todoPostCatalogue.find(id);

        if (!allowed(projectId) || !project.getTodoPosts().contains(todoPost)
                || !todoPost.getAuthor().equals(person)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        //remove from project
        project.getTodoPosts().remove(todoPost);
        DAOFactory.getDAOFactory().getProjectCatalogue().update(project);

        todoPostCatalogue.remove(todoPost.getId());
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(
            @PathParam("projectId") Long projectId,
            @PathParam("id") Long id,
            @FormParam("msg") String msg,
            @FormParam("year") int year,
            @FormParam("month") int month,
            @FormParam("day") int day,
            @FormParam("hour") int hour,
            @FormParam("minute") int minute) {

        if (!allowed(projectId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        TodoPost old = todoPostCatalogue.find(id);
        TodoPost todoPost;
        Person person = (Person) request.getSession().getAttribute("person");
        GregorianCalendar deadline = new GregorianCalendar(year, month, day, hour, minute);
        
        if (old == null) {
            todoPost = new TodoPost(person, msg);
            todoPost.setDeadline(deadline);
            todoPostCatalogue.update(todoPost);
            //add to project
            Project project = DAOFactory.getDAOFactory().getProjectCatalogue().find(projectId);
            project.getTodoPosts().add(todoPost);
            DAOFactory.getDAOFactory().getProjectCatalogue().update(project);
        } else {
            if(!old.getAuthor().equals(person)){
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            todoPost = old;
            todoPost.setMsg(msg);
            todoPost.setDeadline(deadline);
            todoPostCatalogue.update(todoPost);
        }
        return Response.ok(new TodoPostProxy(todoPost)).build();
    }
}
