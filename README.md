rest-example
This is a project that demonstrates the following technologies
   - RESTful Spring component with CRUD interface
   - Jetty application container and runtime
   - Jersey open source JAX-RS annotations
The service simply stores "messages" (text strings) to an in-memory data store.

To build the application, use Maven and run "mvn compile" on the root POM.
To run the application use Maven and run "mvn jetty:run" to start the app in Jetty.

You can use a REST client in the browser to ping the application at
   - http://localhost:8080/<root-context>/rest/messages/

Root context is the application deploy context which is "" in Jetty.   
Ensure that the correct HTTP method : (GET, POST, PUT, DELETE) is used.
The parameters and data types are specified via annotation in the service code.
Use the request header "Content-Type" to specify the request body data type.
Use the request header "Accept" to specify response body data type.

You can also use the scripts to Curl the data to and from the REST service.
The scripts depend on cURL (which comes with MinGw) or can be installed .
   - apt-get install curl

Dependencies of this project are located in POM.xml
   - Java - http://www.java.com
   - Maven - http://maven.apache.org/
   - Spring - http://www.springsource.org/
   - JAX-RS - http://jcp.org/aboutJava/communityprocess/final/jsr311/index.html
   - Jersey - http://jersey.dev.java.net/
   - Jetty - http://jetty.codehaus.org/jetty/
   - Unix4J - http://code.google.com/p/unix4j/
