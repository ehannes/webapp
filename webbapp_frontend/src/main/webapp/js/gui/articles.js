
$(function() {

    var ac = projectPlatform.getArticleCatalogue();
    
    $("#showArticles")
            .button()
            .click(function() {
        var deferred = ac.get();
        
        deferred.done(function(articles) {
            $.each(articles.Article, function(index, a) {
                console.log(a);
                $("#data").append("<p>" +  " id: " + a.id + " <br/>title: " +
                        a.title + " content: " + a.content + "</p>");
            });
            console.log(articles);
        });
        deferred.fail(function() {
            console.log("fail");
        });
    });

});

//@ sourceURL=articles.js

