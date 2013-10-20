/* 
 * Test of PostCatalogue
 */

// Possible test with curl first 
asyncTest("PostCatalogue.count", function() {
    var deferred = projectPlatform.getPostCatalogue().getCount();
    deferred.done(function(nPosts) {
        ok(nPosts.value === 4, "Test passed");
        //ok( true, "Test passed");
        start();
    });
    deferred.fail(function() {
        ok(false, "Test failed (is Server up??)");
        start();
    });
});

asyncTest("PostCatalogue.getRange", function() {
    var deferred = projectPlatform.getPostCatalogue().getRange(1, 3);
    deferred.done(function(posts) {
        ok(posts.length === 3, "Test passed");
        //ok( true, "Test passed");
        start();
    });
    deferred.fail(function() {
        ok(false, "Test failed (is Server up??)");
        start();
    });
});


asyncTest("PostCatalogue.find", function() {
    var deferred = projectPlatform.getPostCatalogue().find(1);
    deferred.done(function(post) {
        ok(post.id === 1, "Test passed");
        start();
    });
    deferred.fail(function() {
        ok(false, "Test failed (is Server up??)");
        start();
    });
});


