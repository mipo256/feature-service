#!/bin/bash

jps | grep -i feature-service | awk '{print $1}' | xargs kill -SIGKILL