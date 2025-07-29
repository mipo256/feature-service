#!/bin/bash

set -e

mkdir -p "$(dirname $0)/log"

# Regular
java -Dvisualvm.display.name="Regular" \
 -Dspring.application.name="Regular" \
 -Dserver.port=8080 \
 -Dspring.devtools.restart.enabled=false \
 -Xmx200m \
 -Xms10m \
 -jar feature-service-0.0.2-SNAPSHOT/feature-service-0.0.2-SNAPSHOT.jar --spring.profiles.active=h2 \
  &>>log/launch.txt &

# AOT Bean definitions
java -Dvisualvm.display.name="AOT_Bean_definitions" \
 -Dspring.application.name="AOT Bean definitions" \
 -Dserver.port=8081 \
 -Dspring.aot.enabled=true  \
 -Dspring.devtools.restart.enabled=false \
 -Xmx200m \
 -Xms10m \
 -jar feature-service-0.0.2-SNAPSHOT/feature-service-0.0.2-SNAPSHOT.jar --spring.profiles.active=h2 \
  &>>log/launch.txt &

# Regular + AppCDS
java -Dvisualvm.display.name="Regular_AppCDS" \
 -Dspring.application.name="Regular + AppCDS" \
 -Dserver.port=8082 \
 -XX:SharedArchiveFile=application.jsa \
 -Dspring.devtools.restart.enabled=false \
 -Xmx200m \
 -Xms10m \
 -jar feature-service-0.0.2-SNAPSHOT/feature-service-0.0.2-SNAPSHOT.jar --spring.profiles.active=h2 \
 &>>log/launch.txt &

# AOT Bean definitions + AppCDS
java -Dvisualvm.display.name="AOT_Bean_definitions_AppCDS" \
 -Dspring.application.name="AOT Bean definitions + AppCDS" \
 -Dserver.port=8083 \
 -XX:SharedArchiveFile=application.jsa \
 -Dspring.aot.enabled=true \
 -Dspring.devtools.restart.enabled=false \
 -Xmx200m \
 -Xms10m \
 -jar feature-service-0.0.2-SNAPSHOT/feature-service-0.0.2-SNAPSHOT.jar --spring.profiles.active=h2 \
 &>>log/launch.txt &

# sleep 10s

cat log/launch.txt | grep -i "Started FeatureServiceApplication"

# jps | grep -i feature-service | awk '{print $1}' | xargs kill -SIGKILL

truncate -s 0 log/launch.txt
