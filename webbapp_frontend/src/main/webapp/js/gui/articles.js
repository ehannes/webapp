/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// This is neede to debug dynamically downloaded JS in Chrome
//@ sourceURL=articles.js
$(function() {
    

    var nav = new Navigator(projectPlatform.getArticleCatalogue());
    /*************************************
     * 
     * Components (from JQueryUI) and eventhandling
     */
    nav.prev(createTable, fail);
    function fail() {
        createErrorDialog("Can't list!!").dialog("open")
    }
        
    $("#next-button")
            .button()
            .click(function() {
        nav.next(createTable, fail);
        function fail() {
            createErrorDialog("Can't list!!").dialog("open")
        }
    });

    $("#prev-button")
            .button()
            .click(function() {
        nav.prev(createTable, fail);
        function fail() {
            createErrorDialog("Can't list!!").dialog("open")
        }
    });


    $("#add-article")
            .button()
            .click(function() {
        createAddDialog();
    });


    /**********************************************
     *   
     *   Functions for redering tables, dialogs and helper functions
     */
    function createTable(articles) {
        $("tbody").empty();
        $(articles).each(function() {
            var p = this;        
            $("tbody").append("<tr><td> " + this.id + "</td> <td>" + this.content + "</td> <td>" + this.editor + "</td></tr>");
            $("tbody tr").click(function() {createEditDeleteDialog(p);});
        });
    }

    function createAddDialog() {
        clearValidationErrors();
        clearFormDialogData();
        var message = "All forms are required";
        $("#dialog-form").dialog({
            autoOpen: true,
            modal: true,
            stack: true,
            buttons: {
                "Save": function() {
                    var article = getFormDialogData();
                    if (validate(article)) {
                        projectPlatform().getArticleCatalogue().add(article.title, article.content, article.editor)
                        $(this).dialog("close");
                    }
                },
                "Cancel": function() {
                    $(this).dialog("close");
                }
            }
        });
        $('#dialog-message').dialog('option', 'title', 'Delete article');
        $("#dialog-message #msg").text(message);
        return $('#dialog-message');
    }

    // Possible to both edit and delet from same dialog
    function createEditDeleteDialog(article) {
        clearValidationErrors();
        setFormDialogData(article);
        var message = "All forms are required";
        $("#dialog-form").dialog({
            autoOpen: true,
            modal: true,
            stack: true,
            buttons: {
                "Delete": function() {
                    createConfirmDeleteDialog(article.id);
                    $(this).dialog("close");
                },
                "Save": function() {
                    var article = getFormDialogData();
                    if (validate(article)) {
                        projectPlatform.getArticleCatalogue().update(article.id, article.title, article.content, article.editor);
                        $(this).dialog("close");
                    }
                },
                "Cancel": function() {
                    $(this).dialog("close");
                }
                
            }
        });
        $('#dialog-message').dialog('option', 'title', 'Delete article');
        $("#dialog-message #msg").text(message);
        return $('#dialog-message');
    }

    // If delete in above dialog, have to confirm.
    function createConfirmDeleteDialog(id) {
        var message = "Are you sure?";
        $("#dialog-message").dialog({
            autoOpen: true,
            modal: true,
            stack: true,
            buttons: {
                Confirm: function() {
                    projectPlatform.getArticleCatalogue().remove(id);
                    $(this).dialog("close");
                }
            }
        });
        $('#dialog-message').dialog('option', 'title', 'Delete article');
        $("#dialog-message #msg").text(message);
        return $('#dialog-message');
    }

    function createErrorDialog(message) {
         // Using JQueryUI dialog
        $("#dialog-message").dialog({
            autoOpen: false,
            modal: true,
            stack: true,
            buttons: {
                Ok: function() {
                    $(this).dialog("close");
                },
                Cancel: function() {
                    $(this).dialog("close");
                }
            }
        });
        $('#dialog-message').dialog('option', 'title', 'Something went! wrong');
        $("#dialog-message #msg").text(message);
        return $('#dialog-message');
    }

//    function validate(product) {
//        var regex = /^[1-9]\d*(((,\d{3}){1})?(\.\d{0,2})?)$/; // A number
//        if (!regex.test(product.price)) {
//            $("#dialog-form #price").addClass("ui-state-error");
//            return false;
//        }
//        return true;
//    }

    function clearValidationErrors() {
        $("#dialog-form input").each(function() {
            $(this).removeClass("ui-state-error");
        });
    }

    function getFormDialogData() {
        var article = {};
        article.id = $("#dialog-form #id").val();
        article.title = $("#dialog-form #title").val();
        article.content = $("#dialog-form #content").val();
        return article;
    }

    function setFormDialogData(article) {
        $("#dialog-form #id").val(article.id);
        $("#dialog-form #title").val(article.title);
        $("#dialog-form #content").val(article.content);
    }

    function clearFormDialogData() {
        $("#dialog-form #id").val("");
        $("#dialog-form #title").val("");
        $("#dialog-form #content").val("");
    }

});



