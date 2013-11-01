#hisvak
The Hisvak dataset opened up with Solr.

##Hisvak solr config

Download and unpack a Solr version into hisvak

For development, place two symbolic links in
* hisvak/solr-[version]/example/solr to hisvak/src/main/solr
* hisvak/solr-[version]/example/webapps/solr.war to ../../dist/solr-[version].war

##Start
Start the SOLR instance with

hisvak/start.sh [version]

For example

$ ./start.sh 4.5.1

##Build
$ mvn clean package

Which will build the hisvak-1.0-SNAPSHOT.jar

##Dependencies
Place In the solr/lib folder the following jars or symbolic links to the libraries:

* commons-beanutils-1.7.0.jar
* solr-dataimporthandler-extras-4.5.1.jar
* commons-collections-3.2.1.jar
* solr-velocity-4.5.1.jar
* hisvak-1.0-SNAPSHOT.jar
* velocity-1.7.jar
* solr-dataimporthandler-4.5.1.jar
* velocity-tools-2.0.jar

## Known issues
Can't find bundle for base name resources, locale en_US,trace=java.util.MissingResourceException

In this case, place symbolic links or copies of the resources_en_US.properties and resources_nl_nl.properties files in the server classpath.

For example in:
WEB-INF/classes or resources folder