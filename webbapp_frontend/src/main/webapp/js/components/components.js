/* 
 * JQueryUI in action
 */

$(function() {

    $("#btnShow")
            .button()
            .click(function() {
        createDialog().dialog("open");
    });

    function createDialog(message) {
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

});
