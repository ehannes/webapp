/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.frontend.rest;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PostCatalogue;
import com.adde.webbapp.model.dao.ProjectCatalogue;
import com.adde.webbapp.model.dao.WallPostCatalogue;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.Post;
import com.adde.webbapp.model.entity.Project;
import com.adde.webbapp.model.entity.WallPost;
import java.net.URI;
import java.util.LinkedList;
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

@Path("projects/{projectId}/wallposts/{wallPostId}/posts")
public class PostCatalogueResource {

    private final PostCatalogue postCatalogue = DAOFactory.getDAOFactory().getPostCatalogue();
    private final WallPostCatalogue wallPostCatalogue =
            DAOFactory.getDAOFactory().getWallPostCatalogue();
    private final ProjectCatalogue projectCatalogue =
            DAOFactory.getDAOFactory().getProjectCatalogue();
    @Context
    private UriInfo uriInfo;
    @Context
    private HttpServletRequest request;

    private boolean allowed(Long projectId) {
        return projectId==1337;
//        HttpSession session = request.getSession(true);
//        Person person = getPerson();
//        Project project = projectCatalogue.find(projectId);
//        return project != null && (person.equals(project.getAdmin())
//                || project.getCollaborators().contains(person));
//        //getCollaborators can't be null when persisted.
    }

    //Does WallPost exist and does it belong to the project with id projectId?
    private boolean validWallPost(Long projectId, Long wallPostId) {
        boolean result = wallPostCatalogue.find(wallPostId) != null;
        Logger.getAnonymousLogger().log(Level.INFO, "PostCatalogueResource.validWallPost: Wallpost {0} is null: " + result, wallPostId);
        return result;
//        WallPost wp = wallPostCatalogue.find(wallPostId);
//        Project project = projectCatalogue.find(projectId);
//        return project != null && wp != null
//                && project.getWallPosts().contains(wp);
    }
    
    private Person getPerson(){
        Logger.getAnonymousLogger().log(Level.INFO, "PostResource: Looking for Person gustav...");
        Person p = (Person) DAOFactory.getDAOFactory().getPersonCatalogue().getByUserName("gustav");
        if(p == null){
            Logger.getAnonymousLogger().log(Level.INFO, "PostResource: Creating person gustav");
            p = new Person("gustav", "gustav@adde.com", "adde");
            DAOFactory.getDAOFactory().getPersonCatalogue().add(p);
        } else{
            Logger.getAnonymousLogger().log(Level.INFO, "PostResource: Person gustav found.");
        }
        return p;
        //return (Person) request.getSession().getAttribute("person");
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response add(@PathParam("projectId") Long projectId,
            @PathParam("wallPostId") Long wallPostId,
            @FormParam("msg") String msg) {
        if (!allowed(projectId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } else if (!validWallPost(projectId, wallPostId)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Person author = getPerson();
        Post post = new Post(author, msg);
        postCatalogue.add(post);

        //add post (comment) to wall post!
        WallPost wp = wallPostCatalogue.find(wallPostId);
        wp.getComments().add(post);
        wallPostCatalogue.update(wp);

        // Tell client where new resource is (URI to)
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(post.getId())).build(post);
        return Response.created(uri).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll(@PathParam("projectId") Long projectId,
            @PathParam("wallPostId") Long wallPostId) {
        if (!allowed(projectId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } else if (!validWallPost(projectId, wallPostId)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        List<PostProxy> result = new LinkedList<>();
        WallPost wp = wallPostCatalogue.find(wallPostId);
        for (Post p : wp.getComments()) {
            result.add(new PostProxy(p));
        }
        GenericEntity ge = new GenericEntity<List<PostProxy>>(result) {
        };
        return Response.ok(ge).build();
    }

    @Path("range")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getRange(
            @PathParam("projectId") Long projectId,
            @PathParam("wallPostId") Long wallPostId,
            @QueryParam("first") int first, @QueryParam("nItems") int nItems) {

        if (!allowed(projectId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } else if (!validWallPost(projectId, wallPostId)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        List<Post> comments = wallPostCatalogue.find(wallPostId).getComments();
        
        if (first < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else if (first >= comments.size()) {
            return Response.noContent().build();
        }

        List<PostProxy> result = new LinkedList<>();
        for(int i=first; i < first + nItems; i++){
            result.add(new PostProxy(comments.get(i)));
        }
        GenericEntity ge = new GenericEntity<List<PostProxy>>(result) {
        };
        return Response.ok(ge).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(
            @PathParam("projectId") Long projectId,
            @PathParam("wallPostId") Long wallPostId,
            @PathParam("id") long id) {

        if (!allowed(projectId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } else if (!validWallPost(projectId, wallPostId)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Post post = postCatalogue.find(id);
        if (post == null) {
            return Response.noContent().build();
        }
        return Response.ok(new PostProxy(post)).build();
    }

    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCount(
            @PathParam("projectId") Long projectId,
            @PathParam("wallPostId") Long wallPostId) {

        if (!allowed(projectId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } else if (!validWallPost(projectId, wallPostId)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        int nrOfComments = wallPostCatalogue.find(wallPostId).getComments().size();
        return Response.ok(new PrimitiveJSONWrapper<>(nrOfComments)).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response remove(
            @PathParam("projectId") Long projectId,
            @PathParam("wallPostId") Long wallPostId,
            @PathParam("id") long id) {

        if (!allowed(projectId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } else if (!validWallPost(projectId, wallPostId)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        WallPost wp = wallPostCatalogue.find(wallPostId);
        Post post = postCatalogue.find(id);
        Person person = getPerson();

        if (post == null) {
            return Response.noContent().build();
        } else if (!(wp.getAuthor().equals(person) || post.getAuthor().equals(person))) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } else if(!wp.getComments().contains(post)){
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        postCatalogue.remove(post.getId());
        //remove from wallpost
        wp.getComments().remove(post);
        wallPostCatalogue.update(wp);
        
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update( 
            @PathParam("projectId") Long projectId,
            @PathParam("wallPostId") Long wallPostId,
            @PathParam("id") Long id,
            @FormParam("msg") String msg) {

        if (!allowed(projectId)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } else if (!validWallPost(projectId, wallPostId)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Post old = postCatalogue.find(id);
        Post post;
        Person person = getPerson();
        WallPost wp = wallPostCatalogue.find(wallPostId);
        if (old == null) {
            post = new Post(person, msg);
            postCatalogue.update(post);
            //add to wallpost
            wp.getComments().add(post);
            wallPostCatalogue.update(wp);
        } else {
            if(!wp.getComments().contains(old)){
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            if(!old.getAuthor().equals(person)){
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            post = old;
            post.setMsg(msg);
            postCatalogue.update(post);
        }
        return Response.ok(new PostProxy(post)).build();
    }
}
