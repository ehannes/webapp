package com.adde.webbapp.frontend.rest;

import com.adde.webbapp.model.dao.ArticleCatalogue;
import com.adde.webbapp.model.dao.DAOFactory;
import com.adde.webbapp.model.dao.ProjectCatalogue;
import com.adde.webbapp.model.dao.ArticleEditCatalogue;
import com.adde.webbapp.model.entity.Article;
import com.adde.webbapp.model.entity.Person;
import com.adde.webbapp.model.entity.ArticleEdit;
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

/**
 *
 * @author ehannes
 */
@Path("/inside/{projectId}/articles")
public class ArticleCatalogueResource {/*
    private final DAOFactory daoFactory = DAOFactory.getDAOFactory();
    private final ArticleCatalogue articleDAO = daoFactory.getArticleDAO();
    private final ArticleEditCatalogue simpleEditorEntryDAO = daoFactory.getSimpleEditorEntryDAO();
    private final ProjectCatalogue projectDAO = daoFactory.getProjectDAO();
    
    @Context
    private UriInfo uriInfo;
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok(getRangePriv(0, articleDAO.getCount())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response add(@FormParam("id") long projectId, @FormParam("title") String title,
        @FormParam("content") String content, @FormParam("editor") Person editor) {
        Article article = new Article(title, content);
        articleDAO.add(addEditor(projectId, article, editor));

        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(article.getId())).build(article);
        return Response.created(uri).build();
    }
    
    ////////////////////// COPY PASTE CODE BELOW! //////////////////////////////
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response remove(@PathParam("id") long id) {
        Article article = articleDAO.find(id);
        if (article != null) {
            articleDAO.remove(article.getId());
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(@PathParam("projectId") long projectId,
        @PathParam("id") long id, @FormParam("title") String title,
        @FormParam("content") double content, @FormParam("editor") Person editor) {
        Article oldArticle = articleDAO.find(id);
        if(oldArticle != null) {
            addEditor(projectId, oldArticle, editor);
            articleDAO.update(oldArticle);
            return Response.ok(new ArticleProxy(product)).build();
        }
        return Response.notModified("Article not found!").build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") long id) {
        Product temp = privateFind(id);
        if (temp == null) {
            return Response.noContent().build();
        }
        return Response.ok(new ProductProxy(temp)).build();
    }

    @GET
    @Path("range")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getRange(@QueryParam("first") int first, @QueryParam("nItems") int nItems) {
        return Response.ok(getRangePriv(first, nItems)).build();
    }*/

    /* Fungerar för JSON, men inte XML. Vad ska vi wrappa int:en från getCount med? */
    /*@GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCount() {
        return Response.ok(new PrimitiveJSONWrapper<>(catalogue.getCount())).build();
    }
    
    private GenericEntity<List<ProductProxy>> getRangePriv(int first, int nItems) {
        // From inclusive, to exclusive
        List<Product> lst = catalogue.getRange(first, nItems);
        List<ProductProxy> result = new LinkedList<>();
        for(Product p : lst){
            result.add(new ProductProxy(p));
        }
        GenericEntity<List<ProductProxy>> ge = new GenericEntity<List<ProductProxy>>
                (result) {};
        return ge;
    }
    
    private Article addEditor(long projectId, Article article, Person editor) {
        ArticleEdit simpleEditorEntry = new ArticleEdit(editor);
        simpleEditorEntryDAO.add(simpleEditorEntry);
        article.getEditorEntries().add(simpleEditorEntry);
        
        projectDAO.find(projectId).getArticles().add(article);
        
        return article;
    }*/
}