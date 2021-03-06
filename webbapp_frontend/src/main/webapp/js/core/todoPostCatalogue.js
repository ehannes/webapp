var TodoPostCatalogue = function(baseUri) {
    this.baseUri = baseUri;
};

TodoPostCatalogue.prototype = (function() {

    return{
        getAll: function() {
            return $.getJSON(this.baseUri);
        },
        getCount: function() {
            return $.getJSON(this.baseUri + "/count");
        },
        getRange: function(first, nItems) {
            return $.getJSON(this.baseUri + "/range?first=" + first + 
                    "&nItems=" + nItems);
        },
        add: function(msg) {
            return $.post(this.baseUri, {msg: msg});
        },
        update: function(id, msg, year, month, day, hour, minute) {
            return $.ajax({
                url: this.baseUri + "/" + id,
                type: 'PUT',
                data: {id: id, msg: msg, year: year, month: month,
                        day: day, hour: hour, minute: minute}
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