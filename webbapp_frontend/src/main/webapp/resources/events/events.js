/* 
 * Using JQuery to connect listeners to controls
 */
// This is guaranteed to be executed after the DOM has
// been fully contructed. So ... connect controls and listeners here
$(document).ready(function(){
    $("#btn1").on("click", listeners.btnListener);
    $("#btn2").on("dblclick", listeners.btnListener2); 
    // This button doesn't exist yet !!! Do it like this
    $(document).on("click", "#dynamic", listeners.btnListener);
    
    $("#txt").on("mouseenter", listeners.mouseEnter).
    on("mouseleave", listeners.mouseLeave ).
    on("focus", listeners.focus);
    
    $("#link").on("click", listeners.link);
    $("form").on("submit", listeners.submitListener);
});

// A singleton holding all listeners
var listeners = (function(){
    
    var color;  // tmp store
    
    return{
        btnListener: function( e ){
            alert("Event type:" + e.type + ":"  + this.id); // This is owner!!
        },
        
        btnListener2: function(){  // Don't care about event, skip
            alert(this.id); 
            // Add a button
            $("#buttons").append("<input id='dynamic' type='button' value='Dynamic'/>");
        },
        
        mouseEnter: function(){ 
            color = $(this).css("background-color");
            $(this).css("background-color", "gray");
        //  $(this).css("background-color", color); 
        },
        
        mouseLeave: function(){       
            $(this).css("background-color", color);   
        },
        
        focus: function(){
            color = "red";
            $(this).css("background-color", "red");
        },
        
        link: function(e){
            e.preventDefault();  // Will not navigate
            alert(this.id); 
        },
        
        submitListener: function(){
            // Called before the real submit
            alert("Submit " + this.id); // This is owner!!
        }  
    } 
})();



