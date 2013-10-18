/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var ArticleCatalogue = function(baseUri) {
    this.baseUri = baseUri;
};

ArticleCatalogue.prototype = (function(){
    
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
        add: function(title, content, editor) {
            return $.post(this.baseUri, {title: title, content: content, editor: editor});
        },
        update: function(id, title, content, editor) {
            return $.ajax({
                url: this.baseUri + "/" + id,
                type: 'PUT',
                data: {title: title, content: content, editor: editor}
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