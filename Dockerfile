# syntax=docker/dockerfile:experimental
FROM maven:3-eclipse-temurin-17 as builder
WORKDIR /workspace/app
COPY pom.xml .
COPY src src
RUN --mount=type=cache,target=/root/.m2 mvn package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; java -Djarmode=layertools -jar ../*.jar extract)

FROM eclipse-temurin:17-jre as runner
VOLUME /tmp
WORKDIR application
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=builder ${DEPENDENCY}/dependencies/ ./
COPY --from=builder ${DEPENDENCY}/spring-boot-loader/ ./
COPY --from=builder ${DEPENDENCY}/snapshot-dependencies/ ./
COPY --from=builder ${DEPENDENCY}/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]
