#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM maven:3.8.3-openjdk-17
WORKDIR /app
COPY --from=build /target/assemblyVotes-0.0.1-SNAPSHOT.jar /app/assemblyVotes.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/assemblyVotes.jar"]