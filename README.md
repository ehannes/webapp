webapp, course DAT076
=======

Web application with Eric, Joakim, Gustav and Hannes. 

Demo 21/10 11.00-11.30, EA...

UML-diagram is in the wiki.

ArticleEdit is a simple class which belongs to an Article.
It adds the functionality to track editations to articles.
When an article is created/updated an ArticleEdit is created with the editor as key and the time as value.
Later on the entry is used to get for example the latest edition of an article (who made the edition and when).

Unfortunately we did the project a little to big. We didn't see this in time. We're satisfied with our model,
the database-connection and the Servlet and REST services in the frontend. But a lot of work is still undone in the
frontend. Because of this, we've made some simplifications to the REST-resources, just to be able to show something at al
at the webpage. Because of this, the Resources are a bit messy right now. We didn't want to take away to much code here
since it may be confusing. We have done a lot of work to the Resources, and they should function very well. The
thing we got stuck on is the JavaScript and the visualisation of the resources. Hope you will understand this.
