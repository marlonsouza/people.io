FROM maven:latest AS build-stage
COPY . /build
WORKDIR /build
RUN mvn clean package -DskipTests=true

FROM openjdk:11 AS runtime
WORKDIR .
COPY --from=build-stage /build/target/people*.jar people-api.jar
ARG PROPERTIES_FILE=/src/main/resources/application-docker.properties
COPY ${PROPERTIES_FILE} application.properties

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/people-api.jar"]