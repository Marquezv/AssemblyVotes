FROM maven:3.8.3-openjdk-17

RUN mkdir -p /app

COPY target/assemblyVotes-0.0.1-SNAPSHOT.jar /app/assemblyVotes.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/assemblyVotes.jar"]