$(function() {

    var pc = projectPlatform.getPostCatalogue();
    
    $("#showPosts")
            .button()
            .click(function() {
        var deferred = pc.get();
        
        deferred.done(function(posts) {
            $.each(posts.Post, function(index, p) {
                console.log(p);
                $("#data").append("<p>" + "time created: " + p.timeCreated +
                        " id: " + p.id + " <br/>Msg: " + p.msg + 
                        " author: " + p.author + "</p>");
            });
            console.log(posts);
        });
        deferred.fail(function() {
            console.log("fail");
        });
    });

});

//@ sourceURL=posts.js

