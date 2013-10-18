package com.adde.webbapp.frontend.rest;

import com.adde.webbapp.model.dao.ArticleCatalogue;
import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.ProjectCatalogue;
import com.adde.webbapp.model.dao.ArticleEditCatalogue;
import com.adde.webbapp.model.dao.PersonCatalogue;
import com.adde.webbapp.model.entity.Article;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.ArticleEdit;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author ehannes
 */
@Path("articles") ///{projectId}/articles/{username}
public class ArticleCatalogueResource {
    private final DAOFactory daoFactory = DAOFactory.getDAOFactory();
    private final ArticleCatalogue articleCatalogue = daoFactory.getArticleCatalogue();
    private final ArticleEditCatalogue articleEditCatalogue = daoFactory.getArticleEditCatalogue();
    //private final ProjectCatalogue projectCatalogue = daoFactory.getProjectCatalogue();
    
    @Context
    private UriInfo uriInfo;
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok(articleCatalogue.getRange(0, articleCatalogue.getCount())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response add(/*@FormParam("projectId") long projectId,*/ @FormParam("title") String title, 
        @FormParam("content") String content, @FormParam("editor") String username) { //Ta in person eller inte?
        PersonCatalogue personCatalogue = daoFactory.getPersonCatalogue();
        Person editor = personCatalogue.getByUserName(username);
        if(editor != null) {
        
            Article article = new Article(title, content);
            articleCatalogue.add(addEditor(article, editor)); //projectId, 

            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(article.getId())).build(article);
            return Response.created(uri).build();
        } else {
            return Response.notModified("Editor not a known user!").build();
        }
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response remove(@PathParam("id") long id) {
        Article article = articleCatalogue.find(id);
        if (article != null) {
            articleCatalogue.remove(article.getId());
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(//@PathParam("projectId") long projectId,
        @PathParam("id") long id, @FormParam("title") String title,
        @FormParam("content") double content, @FormParam("editor") String username) {
        PersonCatalogue personCatalogue = daoFactory.getPersonCatalogue();
        Person editor = personCatalogue.getByUserName(username);
        
        Article oldArticle = articleCatalogue.find(id);
        if(oldArticle != null) {
            addEditor(oldArticle, editor);//projectId, 
            articleCatalogue.update(oldArticle);
            return Response.ok(new ArticleProxy(oldArticle)).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") long id) {
        Article temp = articleCatalogue.find(id);
        if (temp == null) {
            return Response.noContent().build();
        }
        return Response.ok(new ArticleProxy(temp)).build();
    }

    @GET
    @Path("range")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getRange(@QueryParam("first") int first, @QueryParam("nItems") int nItems) {
        return Response.ok(articleCatalogue.getRange(first, nItems)).build();
    }

    /* Fungerar för JSON, men inte XML. Vad ska vi wrappa int:en från getCount med? */
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCount() {
        return Response.ok(new PrimitiveJSONWrapper<>(articleCatalogue.getCount())).build();
    }
    
    private Article addEditor(Article article, Person editor) { //long projectId, 
        ArticleEdit simpleEditorEntry = new ArticleEdit(editor);
        articleEditCatalogue.add(simpleEditorEntry);
        article.getArticleEditions().add(simpleEditorEntry);
        
        //projectCatalogue.find(projectId).getArticles().add(article);
        
        return article;
    }
}