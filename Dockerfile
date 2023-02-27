FROM maven:3.8.3-openjdk-17

WORKDIR /app
COPY target/assemblyVotes-0.0.1-SNAPSHOT.jar /app/assemblyVotes.jar

ENTRYPOINT ["java", "-jar", "/app/assemblyVotes.jar"]