var TodoPostCatalogue = function(baseUri) {
    this.baseUri = baseUri;
};

TodoPostCatalogue.prototype = (function() {

    return{
        get: function() {
            return $.getJSON(this.baseUri);
        },
        getCount: function() {
            return $.getJSON(this.baseUri + "/count");
        },
        getRange: function(first, nItems) {
            return $.getJSON(this.baseUri + "/range?first=" + first + 
                    "&nItems=" + nItems);
        },
        add: function(name) {
            return $.post(this.baseUri, {name: name});
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
    };
}());