/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.frontend.rest;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.WallPostCatalogue;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.WallPost;
import java.net.URI;
import java.util.ArrayList;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Joakim
 */
@Path("wallposts")
public class WallPostCatalogueResource {
    
    private final WallPostCatalogue wallPostCatalogue = DAOFactory.getDAOFactory().getWallPostDAO();
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response get() {
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
    public Response add(@FormParam("author") Person author,
            @FormParam("msg") String msg) {
        WallPost w = new WallPost(author, msg);
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
    public Response update(@PathParam("id") Long id, @FormParam("author") Person author,
            @FormParam("msg") String msg) {
        WallPost oldWallPost = wallPostCatalogue.find(id);
        if (oldWallPost != null) {
            oldWallPost.setAuthor(author);
            oldWallPost.setMsg(msg);
            wallPostCatalogue.update(oldWallPost);
            return Response.ok(new WallPostProxy(oldWallPost)).build();
        }
        return Response.notModified("WallPost not found").build();
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
