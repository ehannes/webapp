
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
    $("#addProject")
            .button()
            .click(function() {
        createAddDialog();
    });

    function createAddDialog() {
        clearValidationErrors();
        clearFormDialogData();
        var message = "Type in your projects name";
        $("#dialog-form").dialog({
            autoOpen: true,
            modal: true,
            stack: true,
            buttons: {
                "Save": function() {
                    var project = getFormDialogData();

                    pc.add(project.name);
                    $(this).dialog("close");
                },
                "Cancel": function() {
                    $(this).dialog("close");
                }
            }
        });
        $('#dialog-message').dialog('option', 'title', 'Delete project');
        $("#dialog-message #msg").text(message);
        return $('#dialog-message');
    }

    function clearValidationErrors() {
        $("#dialog-form input").each(function() {
            $(this).removeClass("ui-state-error");
        });
    }

    function getFormDialogData() {
        var project = {};
        project.name = $("#dialog-form #name").val();
        return project;
    }

    function clearFormDialogData() {
        $("#dialog-form #name").val("");
    }

});

//@ sourceURL=projects.js
