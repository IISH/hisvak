#!/bin/bash
#
# start.sh
# 
# Should be run from src/main

version=$1
if [ -z "$1" ] ; then
	version=solr-4.5.1
fi

p=$(pwd)

if [ ! -d $p/$version ] ; then
    echo "Solr application not found: $p/$version"
    echo "start.sh must be run from folder 'hisvak'"
    exit -1
fi

lib=$p/src/solr/lib
if [ ! -d $lib ] ; then
    echo "Solr library not found: $p/$lib"
    exit -1
fi

rm $lib/*.jar
ln -s $p/$version/contrib/velocity/lib/velocity-tools-2.0.jar $lib/velocity-tools-2.0.jar
ln -s $p/$version/contrib/velocity/lib/velocity-1.7.jar $lib/velocity-1.7.jar
ln -s $p/$version/contrib/velocity/lib/commons-beanutils-1.7.0.jar $lib/commons-beanutils-1.7.0.jar
ln -s $p/$version/contrib/velocity/lib/commons-collections-3.2.1.jar $lib/commons-collections-3.2.1.jar
ln -s $p/$version/dist/solr-velocity-4.5.1.jar $lib/solr-velocity-4.5.1.jar
ln -s $p/$version/dist/solr-dataimporthandler-4.5.1.jar $lib/solr-dataimporthandler-4.5.1.jar
ln -s $p/$version/dist/solr-dataimporthandler-extras-4.5.1.jar $lib/solr-dataimporthandler-extras-4.5.1.jar
ln -s $p/target/hisvak-1.0-SNAPSHOT.jar $lib/hisvak-1.0-SNAPSHOT.jar

cd $p/$version/example
cmd="java -Dsolr.admin.enabled=true -Dsolr.solr.home=$p/src/solr -Dsolr.dist=$p/$version -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar start.jar"
echo $cmd
$cmd
