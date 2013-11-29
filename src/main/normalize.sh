#!/bin/bash
#
# Should be run from src/main

cd ../..
p=$(pwd)

if [ ! -d $p/src/main/groovy/split.groovy ] ; then
    echo "Cannot find $p/groovy/split.groovy"
    exit -1
fi

if [ ! -d $p/import ] ; then
	mkdir $p/import
fi

cd $p/import
groovy groovy/split.groovy HISVAK.xml HISVAK_BRON.xml HISVAK_LID.xml
