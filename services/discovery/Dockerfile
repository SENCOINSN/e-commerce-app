FROM openjdk:17-jdk-slim
ADD target/discovery-0.0.1-SNAPSHOT.jar discovery-0.0.1-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "discovery-0.0.1-SNAPSHOT.jar"]