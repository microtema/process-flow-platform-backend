#!/bin/sh

mvn test
mvn package

docker build -t microtema/process-flow-platform-backend:1.0.3 .

docker push microtema/process-flow-platform-backend:1.0.3

kubectl apply -f deployment.yaml
