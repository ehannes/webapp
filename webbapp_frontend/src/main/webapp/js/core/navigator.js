var Navigator = function(container) {
    // These ar not private just unique for each object
    this.container = container;
    this.fst = 0;
    this.max = 3;
};

// Common methods for all objects   
Navigator.prototype = (function() {

    // Public API
    return {
        next: function(success, fail) {
            // Must save this, will change inside $.when()
            var me = this;
            me.container.getCount().then(function(result) {
                // Move fst pointer
                me.fst = (me.fst + me.max < result.value) ? me.fst + me.max : me.fst;
                // How many items for last listing?
                var m = (me.fst + me.max > result.value) ? result.value - me.fst : me.max;
                return m;
            }, fail).then(function(m) {
                me.container.getRange(me.fst, m).then(success, fail);
            });
        },
        prev: function(success, fail) {
            var me = this;
            me.fst = (me.fst - me.max > 0) ? me.fst - me.max : 0;
            me.container.getCount().then(function(result) {
                var m = (me.fst + me.max > result.value) ? result.value - me.fst : me.max;
                return m;
            }, fail).then(function(m) {
                me.container.getRange(me.fst, m).then(success, fail);
            });
        }
    };
}());
