/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var PersonCatalogue = function(baseUri) {
    this.baseUri = baseUri;
};

PersonCatalogue.prototype = (function(){
    
    return{
        get: function() {
            return $.getJSON(this.baseUri + "/");
        },
// dont know if we need yet                
//        getByName: function(name) {
//            return $.getJSON(this.baseUri + "/byname/" + name);
//        },
//        getCount: function() {
//            return $.getJSON(this.baseUri + "/count");
//        },
        add: function(username, email, password) {
            return $.post(this.baseUri, {username: username, email: email, password: password});
        },
        update: function(id, username, email, password) {
            return $.ajax({
                url: this.baseUri + "/" + id,
                type: 'PUT',
                data: {username: username, email: email, password: password}
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