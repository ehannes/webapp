/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.frontend.rest;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PersonCatalogue;
import com.adde.webbapp.model.dao.WallPostCatalogue;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.Project;
import com.adde.webbapp.model.entity.WallPost;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author Joakim
 */
@Path("projects/{projectId}/wallposts")
//@Path("wallposts")
public class WallPostCatalogueResource {

    private final WallPostCatalogue wallPostCatalogue = DAOFactory.getDAOFactory().getWallPostCatalogue();
    @Context
    private UriInfo uriInfo;
    @Context
    HttpServletRequest request;

//    private boolean allowed(Long projectId) {
//        HttpSession session = request.getSession(true);
//        Object objPerson = session.getAttribute("person");
//        if (objPerson != null) {
//            Person person = (Person) objPerson;
//            Project project = DAOFactory.getDAOFactory().getProjectCatalogue().find(projectId);
//            if (project.getAdmin().equals(objPerson) || project.getCollaborators().contains(person)) {
//                return true;
//            }
//        }
//        return false;
//    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//        public Response get(@PathParam("projectId") Long projectId) {
//        if (!allowed(projectId)) {
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
    public Response get(){
        List<WallPost> wallPosts = wallPostCatalogue.getAll();
        return Response.ok(toWallPostProxy(wallPosts)).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response find(@PathParam("id") long id) {
        WallPost wallPost = wallPostCatalogue.find(id);
        GenericEntity<WallPostProxy> ge = new GenericEntity<WallPostProxy>(new WallPostProxy(wallPost)) {
        };

        if (wallPost != null) {
            return Response.ok(ge).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response add(@FormParam("msg") String msg) {
        //HttpSession session = request.getSession(true);
        //Person person = (Person) session.getAttribute("person");
        
        
        
        /*
        
        Person tmpPerson = new Person("tmpPerson", "tmp@tmp.com", "tM3512");
        PersonCatalogue personCatalogue = DAOFactory.getDAOFactory().getPersonCatalogue();
        personCatalogue.add(tmpPerson);
        
        */
        
        Logger.getAnonymousLogger().log(Level.INFO, "WallPostResource: Looking for Person gustav...");
        Person tmpPerson = (Person) DAOFactory.getDAOFactory().getPersonCatalogue().getByUserName("gustav");
        if(tmpPerson == null){
            Logger.getAnonymousLogger().log(Level.INFO, "WallPostResource: Creating person gustav");
            tmpPerson = new Person("gustav", "gustav@adde.com", "adde");
            DAOFactory.getDAOFactory().getPersonCatalogue().add(tmpPerson);
        } else{
            Logger.getAnonymousLogger().log(Level.INFO, "WallPostResource: Person gustav found.");
        }
        
        
        WallPost w = new WallPost(tmpPerson, msg);
        //WallPost w = new WallPost(person, msg);
        try {
            wallPostCatalogue.add(w);

            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(w.getId())).build(w);
            return Response.created(uri).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") long id) {
        try {
            wallPostCatalogue.remove(id);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Long id, @FormParam("msg") String msg) {
        WallPost oldWallPost = wallPostCatalogue.find(id);
        if (oldWallPost != null) {
            oldWallPost.setMsg(msg);
            wallPostCatalogue.update(oldWallPost);
            return Response.ok(new WallPostProxy(oldWallPost)).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("range")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getRange(@QueryParam("first") int first,
            @QueryParam("nItems") int nItems) {
        List<WallPost> wallposts = wallPostCatalogue.getRange(first, nItems);
        return Response.ok(toWallPostProxy(wallposts)).build();
    }
    
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCount() {
        return Response.ok(new PrimitiveJSONWrapper(wallPostCatalogue.getCount())).build();
    }
    
    private GenericEntity<List<WallPostProxy>> toWallPostProxy(List<WallPost> wallPosts) {
        List<WallPostProxy> wallPostProxies = new ArrayList<>();
        for (WallPost w : wallPosts) {
            wallPostProxies.add(new WallPostProxy(w));
        }
        GenericEntity<List<WallPostProxy>> ge = new GenericEntity<List<WallPostProxy>>(wallPostProxies) {
        };
        return ge;
    }
}
