#!/bin/bash

# Figure out script absolute path
pushd `dirname $0` > /dev/null
SCRIPT_DIR=`pwd`
popd > /dev/null

ROOT_DIR=`dirname $SCRIPT_DIR`

# Find JQF classes and JARs
project="GBF"
version="1.0-SNAPSHOT"

GBF_DIR="${ROOT_DIR}/GBF/target"

GBF_JAR="${GBF_DIR}/$project-$version.jar"

# Compute classpaths (the /classes are only for development; 
#   if empty the JARs will have whatever is needed)
GBF_CLASSPATH="${GBF_DIR}/classes:${GBF_JAR}"

# If user-defined classpath is not set, default to '.'
if [ -z "${CLASSPATH}" ]; then
  CLASSPATH="."
fi  

# Run Java
if [ -n "$JAVA_HOME" ]; then
    java="$JAVA_HOME"/bin/java
else
    java="java"
fi

"$java" -ea \
  -cp "${GBF_CLASSPATH}:${CLASSPATH}" \
  -Djanala.conf="${SCRIPT_DIR}/janala.conf" \
  ${JVM_OPTS} \
  $@

