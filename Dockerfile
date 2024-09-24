FROM gradle:7.5.1-jdk17 AS builder

WORKDIR /app

COPY . .

RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim

ENV SPRING_PROFILE=docker \
    POSTGRES_USER=postgres \
    POSTGRES_PASSWORD=habargde \
    PGHOST=localhost \
    PGPORT=5432 \
    POSTGRES_DB=my_bpm \
    PORT=8081 \
    MONGO_USER=mongo \
    MONGO_PASSWORD= \
    MGHOST=localhost \
    MGPORT=27017 \
    MONGO_DB=my_bpm

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app.jar"]
