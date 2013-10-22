/* 
 * The projectPlatform as a Singleton
 */
// Global
var projectPlatform = (function() {

    var baseUri = "http://localhost:8080/webbapp_frontend/content/rs/";
    
    //don't know if it is right locations
    var persons = new PersonCatalogue(baseUri + "persons");
    var projects = new ProjectCatalogue(baseUri + "projects");
    var wallPosts = new WallPostCatalogue(baseUri + "wallposts");
    var todoPosts = new TodoPostCatalogue(baseUri + "todoposts");
    var posts = new PostCatalogue(baseUri + "posts");
    var articles = new ArticleCatalogue(baseUri + "articles");

    return {
        getProjectCatalogue: function() {
            return projects;
        },
        getWallPostCatalogue: function() {
            return wallPosts;
        },
        getPersonCatalogue: function() {
            return persons;
        },
        getTodoPostCatalogue: function() {
            return todoPosts;
        },
        getPostCatalogue: function() {
            return posts;
        },
        getArticleCatalogue : function() {
            return articles;
        },
        getBaseUri: function() {
            return baseUri;
        }
    };
})();

