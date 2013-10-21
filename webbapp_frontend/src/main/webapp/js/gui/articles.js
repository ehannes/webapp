$(function() {

    var ac = projectPlatform.getArticleCatalogue();
    
    $("#showArticles")
            .button()
            .click(function() {
        $("#articles").empty();
        var deferred = ac.get();
        
        deferred.done(function(articles) {
            console.log(articles);
            $.each(articles.article, function(index, a) {
                $("#articles").append("<div><h3>" + a.title+ "</h3></div>");
                $("#articles").append("<div><p>" + a.content + "</p>" + 
                        "<footer class='text-right'><i><small> Last edited: " + 
                        a.articleEditions.editTime + "</small></i></footer></div>");
            });
            console.log(projects);
        });
        deferred.fail(function() {
            console.log("fail");
        });
    });

});

//@ sourceURL=articles.js

