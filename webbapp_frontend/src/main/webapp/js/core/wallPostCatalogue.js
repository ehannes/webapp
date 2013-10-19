/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var WallPostCatalogue = function(baseUri) {
    this.baseUri = baseUri;
};

WallPostCatalogue.prototype = (function(){
    
    return{
        get: function() {
            return $.getJSON(this.baseUri + "/");
        },
// dont know if we need yet                
//        getByName: function(name) {
//            return $.getJSON(this.baseUri + "/byname/" + name);
//        },
        getCount: function() {
            return $.getJSON(this.baseUri + "/count");
        },
        add: function(msg) {
            return $.post(this.baseUri, {msg: msg});
        },
        update: function(id, msg) {
            return $.ajax({
                url: this.baseUri + "/" + id,
                type: 'PUT',
                data: {msg: msg}
            });
        },
        remove: function(id) {
            return $.ajax({
                url: this.baseUri + "/" + id,
                type: 'DELETE'
            });
        },
        find: function(id) {
            return $.getJSON(this.baseUri + "/" + id);
        }
//        getRange: function(start, nItems) {
//            return $.getJSON(this.baseUri + "/range/", {start: start, nItems: nItems});
//        }
    };
}());

