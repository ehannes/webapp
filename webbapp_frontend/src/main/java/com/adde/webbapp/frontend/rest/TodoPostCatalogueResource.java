
package com.adde.webbapp.frontend.rest;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.ProjectCatalogue;
import com.adde.webbapp.model.dao.TodoPostCatalogue;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.TodoPost;
import java.net.URI;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
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



/**
 * This class had support for authentication of the user as well as
 * for the project id and any eventual todopost id. However, this has been
 * removed since we didn't get the filter working that well.
 * If you want to take a look, see branches from earlier than the 22/10 2013.
 * 
 * @author Gustav
 */
//@Path("projects/{projectId}/todoposts")
@Path("todoposts")
public class TodoPostCatalogueResource {

    private final TodoPostCatalogue todoPostCatalogue =
            DAOFactory.getDAOFactory().getTodoPostCatalogue();
    private final ProjectCatalogue projectCatalogue =
            DAOFactory.getDAOFactory().getProjectCatalogue();
    @Context
    private UriInfo uriInfo;
    @Context
    private HttpServletRequest request;

    private Person getPerson(){
        Logger.getAnonymousLogger().log(Level.INFO, "TodoPostResource: Looking for Person gustav...");
        Person p = (Person) DAOFactory.getDAOFactory().getPersonCatalogue().getByUserName("gustav");
        if(p == null){
            Logger.getAnonymousLogger().log(Level.INFO, "TodoPostResource: Creating person gustav");
            p = new Person("gustav", "gustav@adde.com", "adde");
            DAOFactory.getDAOFactory().getPersonCatalogue().add(p);
        } else{
            Logger.getAnonymousLogger().log(Level.INFO, "TodoPostResource: Person gustav found.");
        }
        return p;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response add(
            @FormParam("msg") String msg) {
        Person author = getPerson();
        TodoPost todoPost = new TodoPost(author, msg);
        todoPostCatalogue.add(todoPost);

        // Tell client where new resource is (URI to)
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(todoPost.getId())).build(todoPost);
        return Response.created(uri).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<TodoPostProxy> result = new LinkedList<>();
        for (TodoPost p : todoPostCatalogue.getAll()) {
            result.add(new TodoPostProxy(p));
        }
        GenericEntity ge = new GenericEntity<List<TodoPostProxy>>(result) {
        };
        return Response.ok(ge).build();
    }

    @GET
    @Path("range")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getRange(
            @QueryParam("first") int first,
            @QueryParam("nItems") int nItems) {

        if (first < 0 || nItems < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else if (first >= todoPostCatalogue.getCount()) {
            return Response.noContent().build();
        }

        List<TodoPostProxy> result = new LinkedList<>();
        nItems = Math.min(first + nItems, todoPostCatalogue.getCount());
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
            @PathParam("id") long id) {

        TodoPost todoPost = todoPostCatalogue.find(id);
        if (todoPost == null) {
            return Response.noContent().build();
        }
        return Response.ok(new PostProxy(todoPost)).build();
    }

    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCount() {
        return Response.ok(new PrimitiveJSONWrapper<>(todoPostCatalogue.getCount())).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response remove(
            @PathParam("id") long id) {

        Person person = getPerson();
        TodoPost todoPost = todoPostCatalogue.find(id);

        if (!todoPost.getAuthor().equals(person)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        todoPostCatalogue.remove(todoPost.getId());
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(
            @PathParam("id") Long id,
            @FormParam("msg") String msg,
            @FormParam("year") int year,
            @FormParam("month") int month,
            @FormParam("day") int day,
            @FormParam("hour") int hour,
            @FormParam("minute") int minute) {

        TodoPost old = todoPostCatalogue.find(id);
        TodoPost todoPost;
        Person person = getPerson();
        GregorianCalendar deadline = new GregorianCalendar(year, month, day, hour, minute);
        
        if (old == null) {
            todoPost = new TodoPost(person, msg);
            todoPost.setDeadline(deadline);
            todoPostCatalogue.update(todoPost);
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
