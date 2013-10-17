/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.frontend.rest;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PostDAO;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.Post;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
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

@Path("/inside/post")
public class PostDAOResource {

    private final PostDAO postDAO = DAOFactory.getDAOFactory().getPostDAO();
    @Context
    private UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response add(@FormParam("msg") String msg, @FormParam("authorId") Long authorId) {
        Person author = DAOFactory.getDAOFactory().getPersonDAO().find(authorId);
        if(author == null){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        Post product = new Post(author, msg);
        postDAO.add(product);
        // Tell client where new resource is (URI to)
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(product.getId())).build(product);
        return Response.created(uri).build();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<PostProxy> result = new LinkedList<>();
            for(Post p : postDAO.getAll()){
                result.add(new PostProxy(p));
            }
            GenericEntity ge = new GenericEntity<List<PostProxy>>
                (result) {};
        return Response.ok(ge).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getRange(@QueryParam("first") int first,
            @QueryParam("nItems") int nItems) {
        if(first < 0 || (first + nItems) > postDAO.getCount()){
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else{
            List<PostProxy> result = new LinkedList<>();
            for(Post p : postDAO.getRange(first, nItems)){
                result.add(new PostProxy(p));
            }
            GenericEntity ge = new GenericEntity<List<PostProxy>>
                (result) {};
            return Response.ok(ge).build();
        }
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") long id) {
        Post post = null;
        for (Post tmpPost : postDAO.getAll()) {
            if (tmpPost.getId() == id) {
                post = tmpPost;
                break;
            }
        }
        if (post == null) {
            return Response.noContent().build();
        }
        return Response.ok(new PostProxy(post)).build();
    }
    
    /* Fungerar för JSON, men inte XML. Vad ska vi wrappa int:en från getCount med? */
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCount() {
        return Response.ok(new PrimitiveJSONWrapper<>(postDAO.getCount())).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response remove(@PathParam("id") long id) {
        Post post = postDAO.find(id);
        if (post != null) {
            postDAO.remove(post.getId());
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(@PathParam("id") Long id,
            @FormParam("msg") String msg, @FormParam("authorId") Long authorId) {
        Post old = postDAO.find(id);
        Post post;
        if (old == null) {
            Person author = DAOFactory.getDAOFactory().getPersonDAO().find(authorId);
            if (author != null) {
                post = new Post(author, msg);
                postDAO.update(post);
            }
            return Response.notModified().build();
        } else {
            post = old;
            post.setMsg(msg); //don't allow changing author...
            postDAO.update(post);
        }
        return Response.ok(new PostProxy(post)).build();
    }
}
