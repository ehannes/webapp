var WallPostCatalogue = function(baseUri) {
    this.baseUri = baseUri;
};

WallPostCatalogue.prototype = (function(){
    
    return{
        get: function() {
            return $.getJSON(this.baseUri);
        },
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
        },
        getRange: function(first, nItems) {
            return $.getJSON(this.baseUri + "/range/", {first: first, nItems: nItems});
        }
    };
}());

