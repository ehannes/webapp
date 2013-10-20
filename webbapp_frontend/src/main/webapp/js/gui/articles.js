
$(function() {

    var ac = projectPlatform.getArticleCatalogue();

    $("#showArticles")
            .button()
            .click(function() {
        var deferred = ac.get();

        deferred.done(function(articles) {
            $.each(articles.Article, function(index, a) {
                console.log(a);
                $("#data").append("<p>" + " id: " + a.id + " <br/>title: " +
                        a.title + " content: " + a.content + "</p>");
            });
            console.log(articles);
        });
        deferred.fail(function() {
            console.log("fail");
        });
    });

    $("#addArticle")
            .button()
            .click(function() {
        createAddDialog();
    });
    
    function createAddDialog() {
        clearValidationErrors();
        clearFormDialogData();
        var message = "Title and content";
        $("#dialog-form").dialog({
            autoOpen: true,
            modal: true,
            stack: true,
            buttons: {
                "Save": function() {
                    var article = getFormDialogData();
                        ac.add(article.title, article.content);
                        $(this).dialog("close");
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

    function clearValidationErrors() {
        $("#dialog-form input").each(function() {
            $(this).removeClass("ui-state-error");
        });
    }

    function getFormDialogData() {
        var article = {};
        article.title = $("#dialog-form #title").val();
        article.content = $("#dialog-form #content").val();
        return article;
    }
    function clearFormDialogData() {
        $("#dialog-form #title").val("");
        $("#dialog-form #content").val("");
    }
});

//@ sourceURL=articles.js

