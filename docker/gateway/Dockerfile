# Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY ../../services/gateway/pom.xml .
RUN mvn dependency:go-offline
COPY ../../services/gateway/src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:17
#ARG PROFILE=dev
#ARG APP_VERSION=0.0.1-SNAPSHOT

WORKDIR /app
COPY --from=build /build/target/*.jar /app/

EXPOSE 8222


#ENV ACTIVE_PROFILE=${PROFILE}
#ENV JAR_VERSION=${APP_VERSION}

CMD java -jar gateway-1.0.0.jar