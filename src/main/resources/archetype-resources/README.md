Restlet Demo Application With RAML Documentation
================================================

Overview
--------

An example application using Restlet Framework, Apache DeltaSpike, and the Restlet RAML module. This was an attempt
which did not completely succeed (Restlet Version 2.3.1). The RAML introspection is not complete and does not generate 
JSON/XML schemas or examples. The archetype will be updated with each release of Restlet Framework and hopefully it
will eventually work as originally intended.


Prerequisites
-------------
* Java >= 1.8
* Maven >= 3.3.1

Building
--------

```bash
mvn clean test
```

Running
-------

```bash
mvn exec:java
```