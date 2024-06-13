# Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY ../../services/order-service/pom.xml .
RUN mvn dependency:go-offline
COPY ../../services/order-service/src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:17
#ARG PROFILE=dev
#ARG APP_VERSION=1.0.0

WORKDIR /app
COPY --from=build /build/target/*.jar /app/

EXPOSE 8070

#ENV DB_URL=jdbc:postgresql://host.docker.internal:5432/bd_payments
#ENV ACTIVE_PROFILE=${PROFILE}
#ENV JAR_VERSION=${APP_VERSION}

CMD java -jar order-service-1.0.0.jar