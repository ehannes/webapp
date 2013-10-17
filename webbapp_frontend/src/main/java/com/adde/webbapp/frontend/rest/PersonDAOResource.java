/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adde.webbapp.frontend.rest;

import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.PersonDAO;
import com.adde.webbapp.model.entity.Person;
import java.net.URI;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
/**
 *
 * @author eric
 */
@Path("person")
public class PersonDAOResource {
   private final PersonDAO personDAO = DAOFactory.getDAOFactory().getPersonDAO();

   @Context
   private UriInfo uriInfo;
   
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response get() {
        List<Person> persons = personDAO.getAll();
        return Response.ok(toPersonProxy(persons)).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response add(@FormParam("username") String name,
            @FormParam("email") String email, 
            @FormParam("password") String password) {
        Person person = new Person(name, email, password);
        try {
            personDAO.add(person);
            
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(person.getId())).build(person);
            return Response.created(uri).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    
    private GenericEntity<List<PersonProxy>> toPersonProxy(List<Person> persons) {
        List<PersonProxy> personProxies = new ArrayList<>();
        for (Person p : persons) {
            personProxies.add(new PersonProxy(p));
        }
        return new GenericEntity<List<PersonProxy>>(personProxies){};
    }
}

