FROM openjdk:17-jdk-alpine

COPY /target/temp-java-2-1.0.0-SNAPSHOT-runner.jar /temp-java-2.jar

EXPOSE 80

ENTRYPOINT ["java","-jar","temp-java-2.jar"]