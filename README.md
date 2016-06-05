# Ibraheem-Samer-WebService-Project

Library Web Service

Samer Bawatni 
Ibraheem Sami





Abstract 
this web service will provide a simple program that can be used by libraries .



Proposed Architecture :

our system will consist of 1 service provider . 
the first provider hosts 11 web services which are :
1-show all books
2-show available books
3-show borrowed books 
4-delete books
5-return books
6-check out books 
7- add books 
8- add a client
9- delete a client
10- get All Clients
11- get Books borrowed by a client


Client :
Our client will be a web  application  that invokes service operations.


Service Providers :
Service provider 1 will host eight services
the first seven services will invoke the seventh service which will read data from database.
the first service will show all the books that the  library owns 
the second service will show all of the books that are availabe and can be borrowed.
the third service will show the borrowed books.
the fourth service allows the admin to delete a book from the database.
the fifth service allows the admin to move a book from the borrowed books to the available books 
the sixth service allows the admin to move a book from the available books to the borrowed books 
the seventh service allows the admin to add a book to the database 


Client : JSF ( JavaServer Faces )   and primeFaces

Service provider will implement the  SOAP style and use the java programming language and will use the GlassFish web server 
and the data layer is MySQL database.
	
	
	






