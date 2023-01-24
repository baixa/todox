#!/bin/sh

# Build JAR packages (without tests, because database has not been defined yet)
mvn clean package dockerfile:build -Dmaven.test.skip

# Create docker network (postgres + jar package)
docker-compose -f docker/docker-compose.yml up -d