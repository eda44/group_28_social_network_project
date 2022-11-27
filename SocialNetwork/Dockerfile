FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER Group28

ARG JAR_FILE=impl/target/impl-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} backend.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["java","-jar","/backend.jar"]