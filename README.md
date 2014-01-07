#hisvak
The Hisvak dataset opened up with Solr.

##Hisvak installation
Clone this git repository. The rootfolder should be 'hisvak'

Download a 4.x Solr distribution from:
http://www.apache.org/dyn/closer.cgi/lucene/solr

and unpack it in the hisvak folder:
hisvak/[the solr version]

##Build
$ mvn clean package

Which will build the hisvak-1.0-SNAPSHOT.jar
The start procedure will arrange the correct placement of this artifact in the Solr lib folder.

##Start
Start the SOLR instance in the hisvak folder:

$ cd hisvak
$ src/main/start.sh [version] ( the version defaults to 4.5.1 )

For example

$ ./start.sh solr-4.5.1

This will place In the solr/lib folder the following jars or symbolic links to the libraries:

* hisvak-1.0-SNAPSHOT.jar
* solr-dataimporthandler-4.5.1.jar
* solr-dataimporthandler-extras-4.5.1.jar
* commons-collections-3.2.1.jar
* solr-velocity-4.5.1.jar
* commons-beanutils-1.7.0.jar
* velocity-1.7.jar
* velocity-tools-2.0.jar


## Known issues
Can't find bundle for base name resources, locale en_US,trace=java.util.MissingResourceException

In this case, place symbolic links or copies of the resources_en_US.properties and resources_nl_nl.properties files in the server classpath.

For example in:
WEB-INF/classes or resources folder
