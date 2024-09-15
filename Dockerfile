FROM eclipse-temurin:17-jdk-alpine
LABEL authors="raphael.costa/Mariana.Sukevicz"

VOLUME /tmp
EXPOSE 8082

ADD target/Microservice_Users-0.0.1-SNAPSHOT.jar UserService.jar
ENTRYPOINT ["java","-jar","/UserService.jar"]