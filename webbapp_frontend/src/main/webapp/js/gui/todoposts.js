// Run after DOM constructed (same as $(document).ready())
$(function() {

    var todoPostCatalogue = projectPlatform.getTodoPostCatalogue();
    
    
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

    function createTable(persons) {
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

//@ sourceURL=persons.js