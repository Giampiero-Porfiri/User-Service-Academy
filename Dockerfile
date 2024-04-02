FROM openjdk:17-jdk-slim AS build
LABEL author="porfirig"

WORKDIR /app

COPY target/*.jar camel-test-b-0.0.1-SNAPSHOT.jar

ENV DOCKERIZE_VERSION v0.6.1
RUN apt-get update && apt-get install -y wget \
    && wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && apt-get clean

ENTRYPOINT ["dockerize", "-wait", "tcp://mysql:3306", "-timeout", "30s", "java", "-jar", "camel-test-b-0.0.1-SNAPSHOT.jar"]