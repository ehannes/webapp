/* 
 * Test of PersonCatalogue
 */

// Possible test with curl first 
asyncTest("PersonCatalogue.count", function() {
    var deferred = projectPlatform.getPersonCatalogue().getCount();
    alert(deferred);
    deferred.done(function(nProducts) {
        ok(nProducts.value === 11, "Test passed");
        //ok( true, "Test passed");
        start();
    });
    deferred.fail(function() {
        ok(false, "Test failed (is Server up??)");
        start();
    });
});

asyncTest("PersonCatalogue.getRange", function() {
    var deferred = projectPlatform.getPersonCatalogue().getRange(1, 3);
    deferred.done(function(products) {
        ok(products.length === 3, "Test passed");
        //ok( true, "Test passed");
        start();
    });
    deferred.fail(function() {
        ok(false, "Test failed (is Server up??)");
        start();
    });
});


asyncTest("PersonCatalogue.find", function() {
    var deferred = projectPlatform.getPersonCatalogue().find(1);
    deferred.done(function(product) {
        ok(product.id === 1, "Test passed");
        start();
    });
    deferred.fail(function() {
        ok(false, "Test failed (is Server up??)");
        start();
    });
});


