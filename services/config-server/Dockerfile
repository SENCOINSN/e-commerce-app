FROM openjdk:17-jdk-slim
ADD /home/runner/work/e-commerce-app/e-commerce-app/services/config-server/target/config-server-0.0.1-SNAPSHOT.jar.original  config-server-0.0.1-SNAPSHOT.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "config-server-0.0.1-SNAPSHOT.jar"]