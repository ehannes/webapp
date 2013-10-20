/* 
 * Test of Navigator
 */

asyncTest("Navigator.next", function() {
    var nav = new Navigator(projectPlatform.getPersonCatalogue());

    nav.next(success, fail);
    
    function success(items) {
        ok(true, "Test passed");
        start();
    }
    function fail() {
        ok(false, "Test failed (is Server up??)");
        start();
    }
});

