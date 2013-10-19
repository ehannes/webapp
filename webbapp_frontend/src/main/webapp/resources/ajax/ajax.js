/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#get").on("click", ajax.get);
    $("#post").on("click", ajax.post); 
});

var ajax = (function(){
    return{
        get: function(){
            var deferred = $.get("AjaxServlet");            
            deferred.done(function( xml ){
                alert("Done " + $(xml).find("response").text());
            });
            deferred.fail(function(){
                alert("Fail"); 
            });
        }, 
        post: function(){
            var data = $("#txt").val();
            var deferred = $.post("AjaxServlet", {
                input: data
            });
            deferred.done(function( xml ){
                alert("Done " + $(xml).find("response").text());
                alert("Response status " + deferred.status);
               // alert("Single Response header " + deferred.getResponseHeaders("Content-Type"));
                alert("Response headers " + deferred.getAllResponseHeaders());
                
            });
            deferred.fail(function(){
                alert("Fail");
            });
        }
    }  
})();


