// Run after DOM constructed (same as $(document).ready())
$(function() {

    var todoPostCatalogue = projectPlatform.getTodoPostCatalogue();
    var nav = new Navigator(projectPlatform.getTodoPostCatalogue());
    
    /*************************************
     * 
     * Components (from JQueryUI) and eventhandling
     */
    
    $("#showPersons")
            .button()
            .click(function() {
        var deferred = todoPostCatalogue.get();
        
        deferred.done(function(todoPosts) {
            $.each(todoPosts.TodoPost, function(index, tp) {
                console.log(tp);
                $("#data").append("<p>" + "time created: " + tp.timeCreated + 
                        " id: " + tp.id + " <br/>message: " + tp.msg + 
                        " author: " + tp.author.userName + " time modified: " + tp.timeModified +
                        "</p>");
            });
            console.log(persons);
        });
        deferred.fail(function() {
            console.log("fail");
        });
    });

    /*$("#add-todoPost")
            .button()
            .click(function() {
        createAddDialog();
    });*/

    //$("#list-todoPosts")
    //        .button()
    //        .click(function() {
    //    createTable();
    //});

    function sendMessage(message) {
        
        //var message = "All forms are required";
        alert(message);
        /*$('#dialog-message').dialog('option', 'msg', 'Delete todoPost');
        $("#dialog-message #msg").text(message);
        return $('#dialog-message');*/
    }

    function createTable(persons) {
        // Use JQuery and HTML
        $("tbody").empty();
        var tbody = $("tbody");
        $(persons).each(function() {
            var s = "<tr id='tr_" + this.id + "'><td>" + this.id +
                    "</td><td>" + this.msg + "</td><td>" + this.author.userName
                    + "</td><td>" + this.timeCreated + "</td><td>" +
                    this.timeModified + "</td></tr>";
            var todoPost = this;
            $(s).appendTo(tbody);
            $("#tr_" + this.id).on("click", function() {
                createEditDeleteDialog(todoPost);
            });
        });
    }
});

// This is neede to debug dynamically downloaded JS in Chrome
//@ sourceURL=persons.js