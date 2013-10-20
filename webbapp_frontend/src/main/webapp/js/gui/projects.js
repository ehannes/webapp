
$(function() {

    var pc = projectPlatform.getProjectCatalogue();

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
//    $("#addProject")
//            .button()
//            .click(function() {
//        createAddDialog();
//    });
//
//    function createAddDialog() {
//        clearValidationErrors();
//        clearFormDialogData();
//        var message = "Type in your projects name";
//        $("#dialog-form").dialog({
//            autoOpen: true,
//            modal: true,
//            stack: true,
//            buttons: {
//                "Save": function() {
//                    var project = getFormDialogData();
//                    if (validate(project)) {
//                        pc.add(project.name);
//                        $(this).dialog("close");
//                    }
//                },
//                "Cancel": function() {
//                    $(this).dialog("close");
//                }
//            }
//        });
//        $('#dialog-message').dialog('option', 'title', 'Delete product');
//        $("#dialog-message #msg").text(message);
//        return $('#dialog-message');
//    }
//    function validate(project) {
//        var regex = /^[1-9]\d*(((,\d{3}){1})?(\.\d{0,2})?)$/; // A number
//        if (!regex.test(project.name)) {
//            $("#dialog-form #name").addClass("ui-state-error");
//            return false;
//        }
//        return true;
//    }
//
//    function clearValidationErrors() {
//        $("#dialog-form input").each(function() {
//            $(this).removeClass("ui-state-error");
//        });
//    }
//
//    function getFormDialogData() {
//        var project = {};
//        project.name = $("#dialog-form #name").val();
//        return project;
//    }
//
//    function clearFormDialogData() {
//        $("#dialog-form #name").val("");
//    }

});

//@ sourceURL=projects.js
