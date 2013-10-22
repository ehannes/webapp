
package com.adde.webbapp.frontend.rest;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PersonCatalogue;
import com.adde.webbapp.model.entity.Person;
import java.net.URI;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
/**
 *
 * @author Eric Ahlberg (eahlberg@gmail.com)
 */
@Path("persons")
public class PersonCatalogueResource {
   private final PersonCatalogue personCatalogue = DAOFactory.getDAOFactory().getPersonCatalogue();

   @Context
   private UriInfo uriInfo;
   
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response get() {
        List<Person> persons = personCatalogue.getAll();
        return Response.ok(toPersonProxy(persons)).build();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response find(@PathParam("id") long id) {
        Person person = personCatalogue.find(id);
                GenericEntity<PersonProxy> ge = new GenericEntity<PersonProxy>(new PersonProxy(person)) {
        };
           
        if (person != null) {
            return Response.ok(ge).build();
        } else {
            return Response.noContent().build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response add(@FormParam("username") String name,
            @FormParam("email") String email, 
            @FormParam("password") String password) {
        Person person = new Person(name, email, password);
        try {
            if(personCatalogue.getByUserName(name) != null) {
                return Response.notModified("Username already taken. Try again.").build();
            }
            personCatalogue.add(person);
            
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(person.getId())).build(person);
            return Response.created(uri).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") long id) {
        try {
            personCatalogue.remove(id);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") long id,
            @FormParam("username") String username,
            @FormParam("email") String email,
            @FormParam("password") String password) {
            Person personToUpdate = personCatalogue.find(id);
            if (personToUpdate != null) {
                personToUpdate.setUserName(username);
                personToUpdate.setEmail(email);
                personToUpdate.setPassword(password);
                personCatalogue.update(personToUpdate);
                return Response.ok(new PersonProxy(personToUpdate)).build();
            }
            return Response.noContent().build();
    }
    
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCount() {
        return Response.ok(new PrimitiveJSONWrapper(personCatalogue.getCount())).build();
    }
    
    @GET
    @Path("range")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getRange(@QueryParam("first") int first,
            @QueryParam("nItems") int nItems) {
        List<Person> persons = personCatalogue.getRange(first, nItems);
        return Response.ok(toPersonProxy(persons)).build();
    }
    
    private GenericEntity<List<PersonProxy>> toPersonProxy(List<Person> persons) {
        List<PersonProxy> personProxies = new ArrayList<>();
        for (Person p : persons) {
            personProxies.add(new PersonProxy(p));
        }
        return new GenericEntity<List<PersonProxy>>(personProxies){};
    }
}

