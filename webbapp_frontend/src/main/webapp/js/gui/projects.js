$(function() {

    var pc = projectPlatform.getProjectCatalogue();
    
    $("#showProjects")
            .button()
            .click(function() {
        $("tbody").empty();
        var deferred = pc.get();
        
        deferred.done(function(projects) {
            console.log(projects);
            $.each(projects.project, function(index, p) {
                $("tbody").append("<tr> <td>" + p.name + "</td> <td>" + 
                    p.dateCreated + "</td><td>" + p.admin.userName + "</td></tr>");
            });
            console.log(projects);
        });
        deferred.fail(function() {
            console.log("fail");
        });
    });

});

//@ sourceURL=projects.js
