# Restlet Archetype For Building DeltaSpike Enabled ReST Microservices

## Overview
--------

This is a [Maven](http://maven.apache.org/) archetype for generating a
basic application skeleton for a ReSTful API service using the
[Restlet Framework](http://restlet.com/technical-resources/restlet-framework/guide/2.3)
in conjunction with [Apache DeltaSpike](http://deltaspike.apache.org/) for dependency
injection. The service created by using this archetype also exposes RAML documentation
generated from the code via the RAML extension for the Restlet Framework.

## Prerequisites

* Java >= 1.8
* Maven >= 3.3.1

## Using

Generating a new project from this archetype requires access to the Maven Central repository via the Internet. If you 
do not have Internet access, you must install this archetype locally and set the additional parameter 
"-DarchetypeCatalog=local"

```bash
mvn archetype:generate -DarchetypeGroupId=com.zanclus.codepalousa -DarchetypeArtifactId=restlet-deltaspike-raml-archetype
cd ${PROJECT_DIR}
mvn clean compile exec:java
```

This will generate the skeleton application and start it running on port 8180. You can view the RAML docs by accessing
http://localhost:8180/rest/v1/raml .

## Features

### Apache DeltaSpike

DeltaSpike is an implementation of a CDI (Contexts & Dependency Injection) container which can be run in Java SE or
inside of a JavaEE application container. It has methods and modules to simplify the use of CDI. To add new resources
for injection consideration you can:

* Annotate the class with one of the CDI scopes (ApplicationScope, Dependent, RequestScoped, etc...)
* Create a producer method. Some producer methods are demonstrated in the **CDIProducer** class of the demo project

### RAML Documentation Generation

Using the Restlet Framework's RAML extension allows for "auto-magic" generation of RAML documentation which is created
by introspecting the code programmatically. This functionality is already enabled and configured in the demo project,
so as long as you create your resource classes properly they will get documented in the RAML output.

### Project Lombok

[Project Lombok](http://projectlombok.org/) is an annotation library which makes it much quicker and easier to write
Java code because it eliminates lots of boiler-plate. Looking at most of the classes, you will see an annotation at the
top which reads **@Slf4j**. This annotation auto-magically wires in a **LOG** object which you can then you to log via
the default logging facility (Log4j2 by default). 

Additionally, if you look at the **ToDo** POJO, you will see the **@Data** annotation. This causes any private fields
in the annotated pojo to automatically have Getters/Setters created as well as a correct **equals** and **hashcode**
method. See the project lombok homepage for more details.

## Making It Your Own

### Use your own database

The demo application which is generated from this archetype is self-contained and fully functional, but it is limited. 
By default it uses an in-memory database engine which you should probably swap out for a different database or at least
reconfigure it to persist data to long-term storage. To accomplish this, you need to do 2 things:

1. Modify the "dependencies" section of the **pom.xml** file in the root of the project to include the appropriate JDBC driver for your database
2. Modify the **src/main/resources/META-INF/persistence.xml** file to set the appropriate database connection settings.

### Create your own document types

The demo application only has one document type, **ToDo**. This is a class which is annotated for serialization/deserialization 
using the **Jackson** databind libraries. By default it can use JSON or XML. The ToDo class is also annotated as a Java
Persistence (JPA) entity which means that the class will be mapped to database entities as configured in 
**src/main/resources/META-INF/persistence.xml** file.

When you add a new document type, it should be a POJO Bean. There is a class in the **test** sources called
**GenericBeanTester**. If you extend this class, you can use the MeanBean bean testing framework to make simple work
of writing unit tests for your document types.

### Add unit tests

Because the application uses Apache DeltaSpike for dependency injection, initial startup can take a few seconds. To 
reduce the amount of time taken when running unit tests, the application uses a CDI Suite runner which starts the CDI
container once and then runs all of the tests. In order to add a new test to this Suite, find the **CdiSuiteLevelTest**
class and add your new unit test classes to the **@Suite.SuiteClasses** annotation.

### Add ReST Resources

Have a look at the **ToDoResource** class to understand how to create a ReST resource. Also, have a look at the **Main**
class to see where the base URL is set. Finally, have a look at the **RestletRamlApplication** class to see how to set
the base URL for your RAML docs and how to "attach" new ReST resources to the request router.