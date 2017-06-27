#!/bin/bash

docker-compose up -d && ./gradlew :consumer-2:bootRun
