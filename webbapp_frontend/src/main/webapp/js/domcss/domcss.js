/* 
 * JQuery DOM CSS manipulation
 * 
 * Run in debugger 
 */
// First defer execution
$(function() {
    
    // Find some element    
    var ul = $("#myList");
    console.log(ul !== null);
    console.log(ul);
    
    // ul is a wrapped element (enhanced with JQuery functionallity)
    // Can use Jquery methods directly  (see debugger)
    ul.attr("class", "myClass");

    // Find children of and traverse
    $("#myList").children().each(function(index, element) {
        // What's this?
        $(this).css("color", "red");
        console.log($(this).text());
    });
    // How to find elements from node sets
    // NOTE: firstChild is the ORIGINAL DOM element *not* wrapped
    // by JQuery (so no fancy methods..)
    var firstChild = $("#myList").children().get(0);
    console.log(firstChild);
    
    // Get parent
    console.log($("#myList").parent());

    // -------------------------------------------------------------------

    // Add an element
    $("<input type='button' value='OK'/>").appendTo("#myDiv");
    
    // ----------------------------------------------------

});