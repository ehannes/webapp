webapp, course DAT076
=======

Java EE web application using Servlets for the login part and a REST API when logged in. 
The REST client is written in xhtml and Javascript using AJAX for retrieving the resources.
Everything is styled with Twitter Bootstrap. 



Testing
=======

Testing of the backend is done with JUnit, testing of the frontend is done with QJUnit. 
The REST resources are tested manually with cURL. A script for initializing some static data as well
as instructions for testing with cURL can be found [here](../../).



Additional resources
=======

More info can be found in the project report [here](../..).
UML-diagram can be found [here](../../wiki/UML-diagram).



How to run
=======

When running the application one needs to create a Derby database with these settings:
name = 'webappDatabase'
username = 'adde'
password = 'adde'

Then just compile the two projects, deploy and enjoy!


/ Eric, Joakim, Gustav and Hannes. 
