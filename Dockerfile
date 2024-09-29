FROM gradle:7.5.1-jdk17 AS builder

WORKDIR /app

COPY . .

RUN gradle build --no-daemon -x test

FROM openjdk:17-jdk-slim

ENV SPRING_PROFILE=local
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=habargde
ENV PGHOST=localhost
ENV PGPORT=5432
ENV POSTGRES_DB=my_bpm
ENV PORT=8081
ENV MONGO_USER=mongo
ENV MGHOST=localhost
ENV MGPORT=27017
ENV MONGO_DB=my_bpm
ENV MONGO_PASSWORD=password

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app.jar"]
