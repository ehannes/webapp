
$(function() {

    var pc = projectPlatform.getPersonCatalogue();
    
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

});

//@ sourceURL=persons.js
