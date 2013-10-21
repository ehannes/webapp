$(function() {

    var pc = projectPlatform.getProjectCatalogue();
    
    $("#createProject")
            .button()
            .click(function() {
        console.log($("#projectName").val());
        pc.add($("#projectName").val());
    });
});

//@ sourceURL=projects.js

//
////$(function() {
//
//    var pc = projectPlatform.getProjectCatalogue();
//    
//    $("#createProject")
//            .button()
//            .click(function() {
//        var project = getFormDialogData();
//        var deferred = pc.add(project.name);
//        console.log(project.name);
//        
//        deferred.done(function(p) {
//            console.log(p);
//        });
//        deferred.fail(function() {
//            console.log("fail");
//        });
//    });
//
//    function getFormDialogData() {
//        var project = {};
//        project.name = $("#projectName").val();
//        return project;
//    }
//});
//
////@ sourceURL=projects.js
