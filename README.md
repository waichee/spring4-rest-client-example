Sample REST client using Spring RestTemplate
================================================
A simple Java standalone application using Spring 4 rest template as REST Client that consumes REST web services (in JSON format).
This example shows both synchronous and asynchronous REST clients options.

Jackson library is used for converting the JSON response.

Wiremock is used as the standalone mock REST Server for the clients to send requests to.

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