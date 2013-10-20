
$(function() {

    var pc = projectPlatform.getPersonCatalogue();
    
    $("#showProjects")
            .button()
            .click(function() {
        var deferred = pc.get();
        
        deferred.done(function(projects) {
            $.each(projects.Project, function(index, p) {
                console.log(p);
                $("#data").append("<p>" + "date created: " + p.dateCreated + 
                        " id: " + p.id + " <br/>name: " + p.name + 
                         " admin: " + p.admin + "</p>");
            });
            console.log(projects);
        });
        deferred.fail(function() {
            console.log("fail");
        });
    });

});

//@ sourceURL=projects.js
