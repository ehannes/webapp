/*
 * Persons.html page 
 * 
 * NOTE: Last in file a comment to enable debugging
 * because this is dynamically loaded (doesn't work by default to debug
 * dynamic scripts in Chrome at least...)
 * 
 */
// Run after DOM constructed (same as $(document).ready())
$(function() {

    var pc = projectPlatform.getPersonCatalogue();
    var nav = new Navigator(projectPlatform.getPersonCatalogue());
    
    /*************************************
     * 
     * Components (from JQueryUI) and eventhandling
     */
    
    $("#showPersons")
            .button()
            .click(function() {
        var deferred = pc.get();
        
        deferred.done(function(persons) {
            $.each(persons.Person, function(index, p) {
                console.log(p);
                $("#data").append("<p>" + "date created: " + p.dateCreated + 
                        " id: " + p.id + " <br/>email: " + p.email + 
                        " username: " + p.username + " password: " + p.password +
                        "</p>");
            });
            console.log(persons);
        });
        deferred.fail(function() {
            console.log("fail");
        });
    });

    /*$("#add-product")
            .button()
            .click(function() {
        createAddDialog();
    });*/

    //$("#list-persons")
    //        .button()
    //        .click(function() {
    //    createTable();
    //});

    function sendMessage(message) {
        
        //var message = "All forms are required";
        alert(message);
        /*$('#dialog-message').dialog('option', 'title', 'Delete product');
        $("#dialog-message #msg").text(message);
        return $('#dialog-message');*/
    }

    function createTable(persons) {
        // Use JQuery and HTML
        $("tbody").empty();
        var tbody = $("tbody");
        $(persons).each(function() {
            var s = "<tr id='tr_" + this.id + "'><td>" + this.id +
                    "</td><td>" + this.name + "</td><td>" + this.email + "</td></tr>";
            var prod = this;
            $(s).appendTo(tbody);
            $("#tr_" + this.id).on("click", function() {
                createEditDeleteDialog(prod);
            });
        });
    }
});

// This is neede to debug dynamically downloaded JS in Chrome
//@ sourceURL=persons.js
