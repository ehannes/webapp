
$(function() {

    var pc = projectPlatform.getPersonCatalogue();
    
    $("#showPersons")
            .button()
            .click(function() {
        $("tbody").empty();
        var deferred = pc.get();
        
        deferred.done(function(persons) {
            $.each(persons.Person, function(index, p) {
                console.log(p);
                $("tbody").append("<tr> <td>" + p.userName + "</td> <td>" + 
                    p.email + "</td></tr>");
            });
            console.log(persons);
        });
        deferred.fail(function() {
            console.log("fail");
        });
    });

});

//@ sourceURL=persons.js
