FROM openjdk:23-jdk

ARG APP_DIR=/app

WORKDIR ${APP_DIR}

COPY .mvn .mvn
COPY src src
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

RUN ./mvnw clean package -Dmaven.tests.skip=true

ENV SERVER_PORT=4000

EXPOSE ${SERVER_PORT}

ENTRYPOINT java -jar target/vtpp5a-ssf-day15l-0.0.1-SNAPSHOT.jar
# Another way to specify entrypoint
# ENTRYPOINT ["java", "-jar", "target/ssf_15l-0.0.1-SNAPSHOT.jar"]



