#FROM ubuntu:latest
#LABEL authors="MR AKINADE AA"
#
#ENTRYPOINT ["top", "-b"]


#FROM ubuntu:latest
##FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ./target/blogapplication-0.0.1-SNAPSHOT.jar app.jar
#LABEL authors="MR AKINADE AA"
#
#ENTRYPOINT ["top", "-b"]
##ENTRYPOINT ["java","-jar","app.jar"]


#FROM ubuntu:latest
FROM openjdk:17-jdk-alpine
LABEL authors="MR AKINADE AA"
ARG JAR_FILE=target/*.jar
COPY ./target/blogapplication-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

#ENTRYPOINT ["top", "-b"]
ENTRYPOINT ["java","-jar","/blogapplication-0.0.1-SNAPSHOT.jar"]
