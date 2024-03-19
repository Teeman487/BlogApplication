#FROM ubuntu:latest
# Use a base image with Java (choose appropriate JDK version)
FROM openjdk:17-jdk-alpine
# Set the working directory inside the container
WORKDIR /app

ARG JAR_FILE=target/*.jar

# Copy the JAR file into the container
COPY ./target/blogapplication-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application listens on
EXPOSE 8080


#ENTRYPOINT ["top", "-b"]
ENTRYPOINT ["java","-jar","/app.jar"]




