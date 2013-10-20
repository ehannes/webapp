var ProjectCatalogue = function(baseUri) {
    this.baseUri = baseUri;
};

ProjectCatalogue.prototype = (function(){
    
    return{
        get: function() {
            return $.getJSON(this.baseUri + "/");
        },
        getCount: function() {
            return $.getJSON(this.baseUri + "/count");
        },
        add: function(name) {
            return $.post(this.baseUri, {name: name});
        },
        update: function(id, name) {
            return $.ajax({
                url: this.baseUri + "/" + id,
                type: 'PUT',
                data: {name: name}
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
        getRange: function(start, nItems) {
            return $.getJSON(this.baseUri + "/range/", {start: start, nItems: nItems});
        }
    };
}());