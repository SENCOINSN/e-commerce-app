# Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY ../../services/notification/pom.xml .
RUN mvn dependency:go-offline
COPY ../../services/notification/src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:17
#ARG PROFILE=dev
#ARG APP_VERSION=1.0.0

WORKDIR /app
COPY --from=build /build/target/*.jar /app/

EXPOSE 8040

#ENV DB_URL=jdbc:postgresql://host.docker.internal:5432/bd_payments
#ENV ACTIVE_PROFILE=${PROFILE}
#ENV JAR_VERSION=${APP_VERSION}

CMD java -jar notification-1.0.0.jar