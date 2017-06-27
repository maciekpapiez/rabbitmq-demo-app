#!/bin/bash

docker-compose up -d && ./gradlew :producer:bootRun
