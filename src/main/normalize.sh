#!/bin/bash
#
# Should be run from src/main

cd ../..
p=$(pwd)

s=$p/src/main/groovy/split.groovy
if [ ! -f $s ] ; then
    echo "Cannot find $s"
    exit -1
fi

d=$p/dataset
if [ ! -d $d/import ] ; then
	mkdir $d/import
fi

cd $d
groovy $s HISVAK.xml HISVAK_BRON.xml HISVAK_LID.xml
