#!/bin/bash

docker-compose up -d && ./gradlew :consumer-1:bootRun
