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

    var nav = new Navigator(projectPlatform.getPersonCatalogue());
    /*************************************
     * 
     * Components (from JQueryUI) and eventhandling
     */
    $("#next-button")
            .button()
            .click(function() {
        nav.next(createTable, fail);
        function fail() {
            createErrorDialog("Can't list!!");
        }
    });

    $("#prev-button")
            .button()
            .click(function() {
        nav.prev(createTable, fail);
        function fail() {
            createErrorDialog("Can't list!!");
        }
    });

    $("#add-product")
            .button()
            .click(function() {
        createAddDialog();
    });

    //$("#list-persons")
    //        .button()
    //        .click(function() {
    //    createTable();
    //});

    function createTable(persons) {
        // Use JQuery and HTML
        $("tbody").empty();
        var tbody = $("tbody");
        var txt = $("bhal");
        $(txt).appendTo(tbody);
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
