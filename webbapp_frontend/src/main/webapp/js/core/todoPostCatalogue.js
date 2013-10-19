/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var TodoPostCatalogue = function(baseUri) {
    this.baseUri = baseUri;
};

TodoPostCatalogue.prototype = (function() {

    return{
        getAll: function() {
            return $.getJSON(this.baseUri + "/all");
        },
        getCount: function() {
            return $.getJSON(this.baseUri + "/count");
        },
        getRange: function(first, nItems) {
            return $.getJSON(this.baseUri + "?first=" + first + 
                    "&nItems=" + nItems);
        },
        add: function(name) {
            return $.post(this.baseUri, {name: name});
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