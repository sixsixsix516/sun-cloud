FROM openjdk:17.0.2-slim-buster

MAINTAINER SUN

ADD target/*.jar /sun-gateway.jar

EXPOSE 9001

ENTRYPOINT["java", "-jar", "sun-user.jar"]
