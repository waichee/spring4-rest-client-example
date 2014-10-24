Sample REST client using Spring RestTemplate
================================================
A simple Java standalone application using Spring 4 Rest template as REST Client.
This example shows both synchronous and asynchronous REST clients options.

http://docs.spring.io/spring/docs/current/spring-framework-reference/html/remoting.html#rest-async-resttemplate

Jackson library is used for converting the JSON response.

Wiremock is used as the standalone mock HTTP Server providing the REST web service in JSON format.

Requirements
============
* Java 1.8
* Maven

Running the App 
===============
Run the AppMain.java class as a Java application

Package as JAR
==============
Execute the following command:
mvn clean package