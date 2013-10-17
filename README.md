webapp, course DAT076
=======

Web application with Eric, Joakim, Gustav and Hannes. 

Demo 21/10 11.00-11.30, EA...

UML-diagram is in the wiki.

SimpleEditorEntry is a simple class which belongs to an Article.
It adds the functionality to track editations to articles.
When an article is created/updated a SimpleEditorEntry is created with the editor as key and the time as value.
Later on the entry is used to get for example the latest edition of an article (who made the edition and when).
