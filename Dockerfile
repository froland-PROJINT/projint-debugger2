# syntax=docker/dockerfile:experimental
FROM maven:3-eclipse-temurin-17 as builder
WORKDIR /workspace/app
COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2 mvn package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jre as runner
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","be.eafcuccle.projint.debugger2.ProjintDebugger2Application"]
