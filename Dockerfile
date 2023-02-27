FROM maven:3.8.3-openjdk-17


COPY target/assemblyVotes-0.0.1-SNAPSHOT.jar assemblyVotes.jar


ENTRYPOINT ["java", "-jar", "assemblyVotes.jar"]