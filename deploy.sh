#!/bin/sh

mvn package

docker build -t microtema/process-flow-platform-backend:1.0.2 .

docker push microtema/process-flow-platform-backend:1.0.2

kubectl apply -f deployment.yaml
